var express = require('express');
var router = express.Router();
var db = require('../db');

router.get('/', function(req, res, next) {
  res.render('index', { title: '게시글목록', pageName: 'posts.ejs' });
});

router.get('/list.json', function(req, res) {
    var sql = 'select *, date_format(wdate, "%Y-%m-%d %T") fdate from view_posts order by id desc';
    db.get().query(sql, function(err, rows) {
        res.send(rows);
    });
});
module.exports = router;
