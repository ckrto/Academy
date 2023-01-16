var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('index', {title : '사용자 목록', pageName : 'users.ejs'});
});

var db = require('../db');

router.get('/list.json', function(req, res) {
  var sql = 'select * from users order by username';
  db.get().query(sql, function(err, rows) {
    res.send(rows);
  });  
});

router.get('/insert', function(req, res) {
  res.render('index', {title:'사용자등록', pageName:'insert.ejs'});
}); 

router.post('/insert', function(req, res) {
  var userid=req.body.userid;
  var username=req.body.username;
  var email=req.body.email;
  var address=req.body.address;
  console.log(userid+"\n"+username+"\n"+email+"\n"+address);
  var sql="insert into users(userid, username, email, address) values(?,?,?,?)"
  db.get().query(sql, [userid, username, email, address], function(err, rows) {
    res.redirect("/users");
  });
});

router.get('/check', function(req, res) {
  var userid=req.query.userid;
  var sql="select * from users where userid=?";
  db.get().query(sql, [userid], function(err, rows) {
    res.send({count : rows.length});
  })
});

module.exports = router;
