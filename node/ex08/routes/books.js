var express = require('express');
const { futimes } = require('fs');
var router = express.Router();
var db = require('../db');

/* GET home page. */
router.get('/read', function(req, res) {
    var code = req.query.code;
    var sql = 'select * from books where code = ?';
    db.get().query(sql, [code], function(err, rows) {
        res.render('index', { title: '도서정보', pageName : 'books/read.ejs', userid : req.session.userid, row:rows[0] });
    });
});

router.get('/review.json', function(req, res) {
    var code = req.query.code;
    var sql = 'select *, date_format(wdate, "%Y-%m-%d") fdate from review where code = ? order by id desc';
    db.get().query(sql, [code], function(err, rows) {
        res.send(rows);
    });
});

router.post('/review/insert', function(req, res) {
    var code = req.body.code;
    var userid = req.session.userid;
    var text = req.body.text;
    var sql = 'insert into review(code, userid, text) values(?,?,?)';
    db.get().query(sql, [code, userid, text], function(err, rows) {
        res.sendStatus(200);
    });
});

router.post('/review/delete', function(req, res) {
    var id = req.body.id;
    var sql = 'delete from review where id = ?';
    db.get().query(sql, [id], function(err, rows) {
        res.sendStatus(200);
    });
});

router.get('/', function(req, res) {
    res.render('index', { title: '도서 목록', pageName : 'list.ejs', userid : req.session.userid});
});

router.get('/list.json', function(req, res) {
    var sql = 'select * from books order by code desc limit 0,10';
    db.get().query(sql, function(err, rows) {
        res.send(rows);
    });
});

module.exports = router;