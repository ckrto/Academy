var express = require('express');
var router = express.Router();
var db = require('../db');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: '한빛미디어', pageName:'home.ejs' });
});

//베스트도서 5권
router.get('/best.json', function(req, res){
  var sql='select * from books order by qnt desc limit 0,5';
  db.get().query(sql, function(err, rows){
    res.send(rows);
  })
});

//도서목록 Data(Json)
router.get('/list.json', function(req, res){
  var page=req.query.page;
  var start=(page-1)*8;
  var query='%' + req.query.query +"%";
  console.log('query.........' + query);
  var count=0;
  var sql='select count(*) cnt from books where title like ?';
  db.get().query(sql,[query],function(err, rows){
    count=rows[0].cnt;
    var sql ='select *,date_format(rdate,"%Y-%m-%d %T") fdate,';
        sql+='format(price,0) fprice ';
        sql+='from books ';
        sql+='where title like ? order by code desc limit ?,8';
    db.get().query(sql,[query, start],function(err, rows){
      res.send({count:count, list:rows});
    })    
  })
})

module.exports = router;
