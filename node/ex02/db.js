var mysql=require('mysql');
var con;
exports.connect = function() {
    con = mysql.createPool ({
        connectionLimit:100,
        host:'localhost',
        user:'node',
        password:'pass',
        database:'nodedb'
    })
}

exports.get = function() {
    return con;
}