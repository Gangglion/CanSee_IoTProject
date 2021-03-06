var express = require('express');
var http = require('http');
const { Socket } = require('socket.io');
var fs = require('fs');
const { Console } = require('console');

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

    socket.on('client_ConnSuccess',function(data){
        console.log('client status : '+data)
    })
    // 파일 목록 달라는 요청에 파일 목록을 반환해줌
    socket.on('ReqFilelist',function(){
        console.log('파일목록 리스트 클라이언트 측으로 전송')

        // var VideoFolder = "./"
        var VideoFolder = "/home/pi/Videos"
        fs.readdir(VideoFolder,function(error,filelist){
            console.log('파일 리스트 : '+filelist)
            socket.emit("ResFilelist",filelist)
        })
    })

    // 특정 파일 클릭했을때 스트림 열어서 그 영상 전송 시작
    socket.on("PlayVideo",function(fileName){
        console.log("플레이 요청 들어옴 : " + fileName)
        var fileResource = "/home/pi/Videos/"+fileName

        var stream = fs.createReadStream(fileResource,{flags:'r'})
        var count = 0
        stream.on('data',function(data){
            count +=1
            console.log('data count = '+count)
            socket.emit("Send",data)
        })
        stream.on('end',function(){
            console.log('End Streaming')
            socket.emit('SendEnd')
        })
    })
})