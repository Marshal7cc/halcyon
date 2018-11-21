/**
 * @name websocket.js
 * @author Marshal.Yuan
 * @description websocket整合到mcap前端
 * @type js
 */

/**
 * mcap-websocket
 * 创建连接
 * @type {null}
 */
var websocket = null;
if ('WebSocket' in window) {
    //Websocket的连接
    websocket = new WebSocket("ws://" + baseContextPathWithoutHttp + "/websocket/socketServer");
}
else if ('MozWebSocket' in window) {
    //Websocket的连接
    websocket = new MozWebSocket("ws://" + baseContextPathWithoutHttp + "/websocket/socketServer");
}
else {
    //SockJS的连接
    websocket = new SockJS("http://" + baseContextPathWithoutHttp + "/sockjs/socketServer");
}
/**
 * websocket事件
 * @type {onOpen}
 */
websocket.onopen = onOpen;
websocket.onmessage = onMessage;
websocket.onerror = onError;
websocket.onclose = onClose;

/**
 * 连接事件
 * @param openEvt
 */
function onOpen(openEvt) {
    //console.log(openEvt);
}

/**
 * 收到消息事件
 * @param evt
 */
function onMessage(evt) {
    var messageInfo = evt.data.split('@');
    $("#content").append('<div class="direct-chat-msg">\n' +
        '                    <div class="direct-chat-info clearfix">\n' +
        '                        <span class="direct-chat-name pull-left">Alexander Pierce</span>\n' +
        '                        <span class="direct-chat-timestamp pull-right">' + messageInfo[1] + '</span>\n' +
        '                    </div>\n' +
        '                    <!-- /.direct-chat-info -->\n' +
        '                    <!--<img class="direct-chat-img" src="../dist/img/user1-128x128.jpg" alt="Message User Image">-->\n' +
        '                    <!-- /.direct-chat-img -->\n' +
        '                    <div class="direct-chat-text">\n' +
        messageInfo[3] +
        '                    </div>\n' +
        '                    <!-- /.direct-chat-text -->\n' +
        '                </div>' + '<br>'); // 接收后台发送的数据
}

function onError() {
}

function onClose() {
}

function doSend() {
    if (websocket.readyState == websocket.OPEN) {
        var targetName = $("#targetName").val();
        var date = new Date();
        var sendDate = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        var sendContent = $("#inputMsg").val();
        $("#inputMsg").val("");
        websocket.send(sendDate + "@" + targetName + "@" + sendContent);//调用后台handleTextMessage方法
        $("#content").append('<div class="direct-chat-msg right">\n' +
            '                    <div class="direct-chat-info clearfix">\n' +
            '                        <span class="direct-chat-name pull-right">Sarah Bullock</span>\n' +
            '                        <span class="direct-chat-timestamp pull-left">' + sendDate + '</span>\n' +
            '                    </div>\n' +
            '                    <div class="direct-chat-text">\n' +
            sendContent +
            '                    </div>\n' +
            '                </div>');
    } else {
        console.log("连接失败!" + websocket.readyState);
    }
}

window.close = function () {
    websocket.onclose();
}