var express = require('express');
var router = express.Router();
var db = require('../db');
var multer = require('multer');
var fs = require('fs');

var upload = multer ({
  storage : multer.diskStorage ({
    destination : (req, file, done) => {
      done(null, './public/photo')
    },
    filename : (req, file, done) => {
      done(null, Date.now() + '_' + file.originalname)
    }
  })
});

router.get('/read', function(req, res) {
  var userid = req.query.userid;
  var sql = 'select * from users where userid = ?';
  db.get().query(sql, [userid], function(err, rows) {
    res.render('index', {title : '회원정보 수정', pageName : 'users/read.ejs', row:rows[0], userid : req.session.userid});
  });
});

router.post('/insert', upload.single('photo'), function(req, res) {
  var userid = req.body.userid;
  var username = req.body.username;
  var password = req.body.password;
  var email = req.body.email;
  var address = req.body.address;
  var photo = req.file.filename;

  console.log(`${userid}\n${username}\n${password}\n${email}\n${address}\n${photo}`);
 
  var sql = 'insert into users(userid, username, password, email, address, photo) values(?,?,?,?,?,?)';
  db.get().query(sql, [userid,username,password,email,address,photo], function(err, rows) {
    res.redirect('/users');
  })
});

router.get('/check', function(req, res) {
  var userid = req.query.userid;
  var sql = 'select * from users where userid=?';
  db.get().query(sql, [userid], function(err, rows) {
    res.send({count:rows.length});
  });
});

router.get('/insert', function(req, res) {
  res.render('index', {title : '회원가입', pageName : 'users/insert.ejs', userid : req.session.userid})
});

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('index', {title : '사용자 목록', pageName : 'users/list.ejs', userid : req.session.userid});
});

router.get('/list.json', function(req, res) {
  var sql = 'select * from users';
  db.get().query(sql, function(err, rows) {
    res.send(rows);
  });
});

router.post('/update', upload.single('photo'), function(req, res) {
  var userid = req.body.userid;
  var username = req.body.username;
  var password = req.body.password;
  var email = req.body.email;
  var address = req.body.address;
  var photo = req.body.old;

  if(req.file != null) {
    photo = req.file.filename;
    fs.unlink('./public/photo/' + req.body.old, function(err){
      console.log('err....' + err);
    });
  }

  var sql = 'update users set username=?, password=?, email=?, address=?, photo=? where userid = ?';
  db.get().query(sql, [username, password, email, address, photo, userid], function(err, rows) {
    res.redirect('/users');
  });
});

router.get('/login', function(req, res) {
  res.render('index', {title : '로그인', pageName : 'users/login.ejs', userid : req.session.userid});
});

router.post('/login', function(req, res) {
  var userid = req.body.userid;
  var password = req.body.password;
  var result = 0;

  var sql = 'select * from users where userid = ?';
  db.get().query(sql, [userid], function(err, rows) {
    if(rows.length > 0) {
      if(rows[0].password == password) {
        result = 1;
        req.session.userid = userid;
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
  res.redirect('/');
})

module.exports = router;
