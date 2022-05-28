var express = require('express');
var http = require('http');

var socketio = require('socket.io')(http);

var app = express();
var port = 3000;
var server = http.createServer(app).listen(port,function(){
    console.log('서버 시작 / 포트 : '+port);
})

var io = socketio.listen(server);
console.log('socket.io요청을 받아들일 준비가 되었습니다.');

io.on('connection',function(socket){
    console.log('연결됨 / info : ',socket.request.connection._peername);
})