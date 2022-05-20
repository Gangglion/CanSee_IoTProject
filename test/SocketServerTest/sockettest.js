// var app = require('express')();
// var http = require('http').Server(app);
// var io = require('socket.io')(http);

// // app.get('/', function (req, res) {
// //     res.sendfile('index.html');
// // });

// http.listen(3000, function () {
//     console.log('listen on * : 3000');
// });

// //원격에서(안드로이드 클라이언트에서) 접속이 되면 기본적으로 실행하는 코드
// //'connection' : socket.io의 기본 이벤트, 사용자가 웹사이트에 접속하면 자동으로 발생하는 이벤트
// io.on('connection', function (socket) {
//     // var addr = socket.address();
//     // console.log('클라이언트 접속됨.%s, %d',addr.address,addr.port)
//     socket.emit('message_fron_server', 'socketTest with android');

//     //메세지를 받으면 응답
//     socket.on('message_from_androidClient', function (msg) {
//         console.log('msg : ', msg);
//         // 받은 메세지를 되돌려주거나, 그 메세지를 이용하여 처리하는 코드가 들어갈 수 있다.
//         // ex) 로그인 시 로그인 메세지 뿌리고, 라우터를 통해 그 메세지가 들어오면 회원가입이나 로그인 등의 처리 가능할 듯 보임
//         socket.emit('message_from_client', ' "' + msg + '" 라고 하셨군요');
//     })
// });

// var http = require('http');

// // 서버 객체 만들기
// var server =http.createServer();

var port = 3000;
server.listen(port,function(){
    console.log('웹서버 시작. %d',port);
});
server.on('connection',function(socket){
    var addr = socket.address();
    console.log('클라이언트 접속됨.%s, %d',addr.address,addr.port);
})