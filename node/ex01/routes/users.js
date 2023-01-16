var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/list', function(req, res, next) {
  res.send([
    {userid : "red", username : "홍길동", email : "red@icia.com"},
    {userid : "blue", username : "강감찬", email : "blue@icia.com"},
    {userid : "green", username : "심청이", email : "green@icia.com"},
    {userid : "pink", username : "성춘향", email : "pink@icia.com"},
    {userid : "gray", username : "이몽룡", email : "gray@icia.com"}
  ]);
});

router.get('/', function(req, res, next) {
  res.render('index', { title: '사용자목록', pageName : 'users.ejs' });
});

module.exports = router;
