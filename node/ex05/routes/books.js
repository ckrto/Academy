var express = require('express');
var router = express.Router();
var db=require("../db");

//책 정보 가져오기
router.get('/read',function(req,res,next){
    var code=req.query.code;
    var sql='select *,date_format(rdate,"%Y-%m-%d") fdate, format(price,0) fprice from books where code=?';
    db.get().query(sql,[code],function(err,rows){
        res.render('index',{title:'도서 정보', pageName:'books/read.ejs',book:rows[0]})
    });
})



module.exports = router;
