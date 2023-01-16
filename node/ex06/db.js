var mysql=require('mysql');
var con;

//db를 커넥트 하는 함수
exports.connect=function(){
    con=mysql.createPool({
        connectionLimit:100,
        host:'localhost',
        user:'node',
        password:'pass',
        database:'nodedb',
        port:'3306'
    })
}


//커넥트 한 db의 데이터를 가져오는 함수
exports.get=function(){
    return con;
}