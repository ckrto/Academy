var express = require('express');
var router = express.Router();
var db = require('../db');

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

router.get('/login', function(req, res) {
  res.render('index', {title : '로그인', pageName : 'users/login.ejs', userid : req.session.userid});
});

router.post('/login', function(req,res) {
  var userid = req.body.userid;
  var password = req.body.password;
  var check = req.body.check;

  var sql = 'select * from users where userid = ?';
  var result = 0;

  db.get().query(sql, [userid], function(err, rows) {
    if(rows.length > 0) {
      if(rows[0].password == password) {
        result = 1;
        req.session.userid = userid;
        if(check == 1) {
          res.cookie('userid', userid, {maxAge : 60 * 60 * 24});
        }
      }
      else {
        result = 2;
      }
    }
    res.send({result:result});
  });
});

router.get('/logout', function(req, res) {
  req.session.destroy();
  res.clearCookie('userid');
  res.redirect('/');
});

module.exports = router;
