var express = require('express');
var router = express.Router();
var db = require('../db');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: '게시판 목록', pageName : 'posts.ejs' });
});

router.get('/list.json', function(req, res) {
 var sql = 'select * from view_posts order by id desc';
 db.get().query(sql, function(err, rows) {
  console.log('err.............' + err);
  res.send(rows);
 })
});

module.exports = router;
