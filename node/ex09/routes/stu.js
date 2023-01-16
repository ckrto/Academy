var express = require('express');
var router = express.Router();
var db = require('../db');

/* GET 학생목록페이지 */
router.get('/', function(req, res, next) {
  res.render('index',{title:'학생목록', pageName:'stu/list.ejs'});
});


//학생목록 데이터
router.get('/list.json', function(req,res){
    var page=req.query.page;
    var start=(page-1)*5;
    var count=0;
    var sql='select count(*) cnt from students';
    db.get().query(sql, function(err, rows){
      count = rows[0].cnt;
      var sql= 'select *,date_format(birthday,"%Y-%m-%d") fbirthday ';
          sql+='from view_stu order by dept limit ?,5';
      db.get().query(sql,[start],function(err, rows){
          res.send({count:count, rows:rows});
      })
    });  
})

//GET 학생정보 페이지
router.get('/update', function(req, res){
  var scode=req.query.scode;
  var sql='select *,date_format(birthday,"%Y-%m-%d") fbirth from view_stu where scode=?';
  db.get().query(sql, [scode], function(err, rows){
    res.render('index', {title:'학생정보', pageName:'stu/update.ejs',
                          row:rows[0]});
  })
})

//특정 학생이 수강신청한 강좌목록
router.get('/cou_list.json', function(req, res){
  var scode=req.query.scode;
  var sql='select *,date_format(edate,"%Y-%m-%d") fdate from enroll_cou where scode=?';
  db.get().query(sql, [scode], function(err, rows){
    res.send(rows);
  })
})

//특정학생의 특정과목 수강신청
router.post('/enroll_insert', function(req, res){
  var scode=req.body.scode;
  var lcode=req.body.lcode;
  var count=0;
  var sql='select * from enrollments where scode=? and lcode=?';
  db.get().query(sql, [scode, lcode], function(err, rows){
    count=rows.length;
    if(count==0){
      sql='insert into enrollments(scode,lcode) values(?,?)';
      db.get().query(sql, [scode, lcode], function(err,rows){
        sql='update courses set persons=persons+1 where lcode=?';
        db.get().query(sql,[lcode],function(err, rows){
          res.send({count:count})
        })
      })
    }else{
      res.send({count:count})
    }
  })
});

//특정학생이 특정과목을 수강취소
router.post('/enroll_delete', function(req, res){
  var scode=req.body.scode;
  var lcode=req.body.lcode;
  var sql='delete from enrollments where scode=? and lcode=?';
  db.get().query(sql, [scode,lcode], function(err, rows){
    console.log('err1.....' + err);
    sql='update courses set persons=persons-1 where lcode=?';
        db.get().query(sql,[lcode],function(err, rows){
          console.log('err2.....' + err);
          res.sendStatus(200);
        })
  })
})
module.exports = router;