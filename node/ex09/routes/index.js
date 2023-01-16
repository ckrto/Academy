var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: '학사관리 시스템',pageName:'home.ejs' });
});

module.exports = router;
