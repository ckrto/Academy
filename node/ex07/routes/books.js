var express = require('express');
var router = express.Router();
var db=require("../db");
var multer = require('multer');

var uploadPath = './public/books'; 
var upload = multer({
    storage: multer.diskStorage({
        destination: (req, file, done) => {
            done(null, uploadPath);
        }, filename: (req, file, done) => { 
            done(null, Date.now()+'_' + file.originalname);
        },
    })
});

/* 최근 도서 5권 */
router.get('/new.json', function(req, res, next) {
  var sql='select *, date_format(rdate, "%Y-%m-%d") fdate from books order by rdate desc limit 0,5';
  db.get().query(sql,function(err,rows){
    res.send(rows);
  });
});

//베스트 도서(판매량) 5권
router.get('/best.json', function(req, res, next) {
    var sql='select *from books order by qnt desc limit 0,5';
    db.get().query(sql,function(err,rows){
      res.send(rows);
    });
  });

  router.get('/list.json', function(req, res) {
    var query = '%' + req.query.query + '%';
    var page = req.query.page;
    var start = (page-1) * 4;
    var count = 0;
    var sql = 'select count(*) cnt from books where title like ?';
    db.get().query(sql, [query], function(err, rows) {
        count = rows[0].cnt;
        var sql = 'select * from books where title like ? order by code desc limit ?,4';
        db.get().query(sql, [query, start], function(err, rows) {
            res.send({count : count, list : rows});
        })
    });
  });

  router.get('/', function(req, res) {
    res.render('index', {title : '도서목록', pageName : 'books/list.ejs', userid : req.session.userid})
  });

  router.post('/update', function(req, res) {
    var code = req.body.code;
    var price = req.body.price;
    var qnt = req.body.qnt;

    var sql = 'update books set price = ?, qnt = ? where code = ?';
    db.get().query(sql, [price,qnt,code], function(err, rows) {
        res.sendStatus(200);
    });
  });

  router.get('/insert', function(req, res) {
    res.render('index', {title : '도서 등록', pageName : 'books/insert.ejs', userid : req.session.userid})
  });

  router.post('/insert', upload.single('image'),function(req, res) {
    var title = req.body.title;
    var price = req.body.price;
    var userid = req.body.userid;
    var image = req.file.filename;
    var sql = 'insert into books(title, price, userid, image) values(?,?,?,?)';
    db.get().query(sql, [title,price,userid,image], function(err, rows) {
        res.redirect('/');
    });
  });

module.exports = router;