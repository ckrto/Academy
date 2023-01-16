var express = require('express');
var router = express.Router();
var db = require('../db');

/*특정강좌를 신청한 학생목록 데이타*/
router.get('/stu_list.json', function(req, res, next) {
  var lcode=req.query.lcode;
  var sql='select *,date_format(edate,"%Y-%m-%d") fdate from enroll_stu where lcode=?';
  db.get().query(sql, [lcode], function(err, rows){
    res.send(rows);
  })
});

//특정강좌를 수강신청한 특정학생의 점수를 수정
router.post('/enroll_update', function(req, res){
    var lcode=req.body.lcode;
    var scode=req.body.scode;
    var grade=req.body.grade;
    var sql='update enrollments set grade=? where scode=? and lcode=?';
    db.get().query(sql,[grade,scode,lcode],function(err, rows){
        res.sendStatus(200);
    })
});


//강좌목록 데이터
router.get('/list.json', function(req, res){
    var sql='select * from view_cou';
    db.get().query(sql, function(err, rows){
        res.send(rows);
    })
})

module.exports = router;