var express = require('express');
var router = express.Router();
var db = require('../db');

/* GET 교수관리 페이지 */
router.get('/', function(req, res, next) {
  res.render('index', {title:'교수목록', pageName:'pro/list.ejs'})
});

// GET 교수목록 데이타
router.get('/list.json', function(req, res){
    var sql='select *,date_format(hiredate,"%Y-%m-%d") fhire,format(salary,0) fsalary from professors';
    db.get().query(sql, function(err, rows){
        res.send(rows);
    })
})

//GET 교수등록 페이지
router.get('/insert', function(req, res){
    var sql='select max(pcode)+1 ncode from professors';
    db.get().query(sql, function(err, rows){
        res.render('index',{title:'교수등록',
                            pageName:'pro/insert.ejs', 
                            ncode:rows[0].ncode})
    })
})

//교수등록
router.post('/insert', function(req, res){
    var pcode=req.body.pcode;
    var pname=req.body.pname;
    var dept=req.body.dept;
    var yy=req.body.yy;
    var mm=req.body.mm;
    var dd=req.body.dd;
    var title=req.body.title;
    var salary=req.body.salary;
    var hiredate=yy + '-' + mm + '-' + dd;
    //console.log(`${pcode}\n${pname}\n${dept}\n${hiredate}\n${title}\n${salary}`);
    var sql='insert into professors(pcode,pname,dept,hiredate,title,salary) ';
    sql += ' values(?,?,?,?,?,?)';
    db.get().query(sql,[pcode,pname,dept,hiredate,title,salary],function(err,rows){
        res.redirect('/pro');
    })
})

//GET 교수정보 수정 페이지
router.get('/update', function(req, res){
    var pcode=req.query.pcode;
    var sql ='select *,date_format(hiredate,"%Y") yy,';
        sql+='date_format(hiredate,"%m") mm,';
        sql+='date_format(hiredate,"%d") dd ';
        sql+='from professors where pcode=?';

    db.get().query(sql,[pcode],function(err, rows){
        res.render('index',{title:'교수정보수정',
                            pageName:'pro/update.ejs',
                            row:rows[0]});
    })
})

//교수정보 수정
router.post('/update', function(req, res){
    var pcode=req.body.pcode;
    var pname=req.body.pname;
    var dept=req.body.dept;
    var yy=req.body.yy;
    var mm=req.body.mm;
    var dd=req.body.dd;
    var title=req.body.title;
    var salary=req.body.salary;
    var hiredate=yy + '-' + mm + '-' + dd;
    var sql= 'update professors set pname=?,dept=?,title=?,';
        sql+='salary=?,hiredate=? where pcode=?';
    db.get().query(sql,[pname,dept,title,salary,hiredate,pcode],
        function(err, rows){
            res.redirect('/pro');
        })    
})

//교수삭제
router.post('/delete', function(req, res){
    var pcode=req.body.pcode;
    var scnt=0;
    var ccnt=0;
    var sql='select count(*) cnt from students where advisor=?';
    db.get().query(sql,[pcode],function(err, rows){
        scnt=rows[0].cnt;
        sql='select count(*) cnt from courses where instructor=?';
        db.get().query(sql,[pcode],function(err, rows){
            ccnt=rows[0].cnt;
            if(scnt==0 && ccnt==0){
                sql='delete from professors where pcode=?';
                db.get().query(sql,[pcode],function(err, rows){
                    res.send({scnt:scnt, ccnt:ccnt});
                })
            }else{
                res.send({scnt:scnt, ccnt:ccnt});
            }
        })
    })
})

//특정교수가 강의하는 강의목록 데이타
router.get('/courses.json', function(req, res){
    var pcode=req.query.pcode;
    var sql='select * from courses where instructor=?';
    db.get().query(sql, [pcode], function(err, rows){
        res.send(rows);
    })
})

module.exports = router;