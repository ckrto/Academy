var express = require('express');
var router = express.Router();
var db = require('../db');

/* GET home page. */
router.get('/', function(req, res, next) {
  if(req.cookies.userid) {
    req.session.userid=req.cookies.userid;
  }
  res.render('index', { title: '회사소개', pageName : 'home.ejs', userid : req.session.userid });
});

router.get('/new.json', function(req, res) {
  var sql = 'select *, date_format(rdate, "%Y-%m-%d")  fdate, format(price, 0) fprice from books order by code desc limit 0,5';
  db.get().query(sql, function(err, rows) {
    res.send(rows);
  })
});

router.get('/best.json', function(req, res) {
  var sql = 'select *, date_format(rdate, "%Y-%m-%d")  fdate, format(price, 0) fprice from books order by qnt desc limit 0,5';
  db.get().query(sql, function(err, rows) {
    res.send(rows);
  })
});

module.exports = router;
