var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  if(req.cookies.userid) {
    req.session.userid=req.cookies.userid;
  }
  res.render('index', { title: '회사소개', pageName : 'home.ejs', userid : req.session.userid });
});

module.exports = router;
