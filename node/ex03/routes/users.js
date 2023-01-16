var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('index', {title:'사용자목록', pageName: 'users.ejs'});
});

var db = require('../db');

router.get('/list.json', function(req, res) {
  var sql = 'select * from users order by username';
  db.get().query(sql, function(err, rows) {
    res.send(rows);
  });  
});

router.get('/insert', function(req, res) {
  res.render('index', {title : '사용자 등록', pageName : 'users/insert.ejs'});
});

router.post('/insert', function(req, res) {
  var userid=req.body.userid;
  var username=req.body.username;
  var email=req.body.email;
  var address=req.body.address;

  var sql = "insert into users(userid, username, email, address) values(?,?,?,?)";
  db.get().query(sql, [userid, username, email, address], function(err, rows) {
    console.log('err...' + err);
    res.redirect('/users');
  });
});

router.get('/check', function(req, res) {
  var userid = req.query.userid;
  var sql = 'select * from users where userid=?';
  db.get().query(sql, [userid], function(err, rows) {
    res.send({count : rows.length});
  }); 
});

router.get('/read', function(req, res) {
  var userid = req.query.userid;
  var sql='select * from users where userid=?';
  db.get().query(sql, [userid], function(err, rows) {
    res.render('index', {title:'사용자정보', pageName:'users/read.ejs', row:rows[0]});
  });
});

router.post('/update', function(req, res) {
  var userid = req.body.userid;
  var username = req.body.username;
  var email = req.body.email;
  var address = req.body.address;

  var sql = "update users set username=?, email=?, address=? where userid=?";
  db.get().query(sql, [username, email, address, userid], function(err, rows) {
    console.log('err...' + err);
    res.redirect('/users');
  });
});

router.post('/delete', function(req, res) {
  var userid=req.body.userid;
  var sql = 'delete from users where userid=?';
  db.get().query(sql, [userid], function(err, rows) {
    res.redirect('/users');
  });
});

//특정사용자가 작성한 게시글 갯수
router.get('/count', function(req, res){
  var userid=req.query.userid;
  var sql='select * from posts where userid=?';
  db.get().query(sql, [userid], function(err, rows){
    res.send({count:rows.length})
  })
})

module.exports = router;
