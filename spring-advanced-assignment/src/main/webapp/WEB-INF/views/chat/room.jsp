<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>STOMP 채팅</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7/bundles/stomp.umd.min.js"></script>
</head>
<body>
<h1>STOMP 채팅방</h1>
<p>
    <label>방 <input id="roomId" value="general"></label>
    <label>이름 <input id="sender" value="guest"></label>
    <button id="connectButton" type="button">연결</button>
</p>
<div id="messages" style="border:1px solid #aaa; min-height:200px; padding:8px;"></div>
<p>
    <input id="message" size="60">
    <button id="sendButton" type="button">전송</button>
</p>
<script>
    let client;
    const contextPath = '${pageContext.request.contextPath}';

    document.getElementById('connectButton').addEventListener('click', function () {
        const roomId = document.getElementById('roomId').value;
        client = new StompJs.Client({
            webSocketFactory: () => new SockJS(contextPath + '/ws-chat'),
            reconnectDelay: 5000,
            onConnect: () => {
                client.subscribe('/topic/chat/' + roomId, frame => {
                    const chat = JSON.parse(frame.body);
                    const line = document.createElement('p');
                    line.textContent = '[' + chat.sentAt + '] ' + chat.sender + ': ' + chat.message;
                    document.getElementById('messages').appendChild(line);
                });
            }
        });
        client.activate();
    });

    document.getElementById('sendButton').addEventListener('click', function () {
        if (!client || !client.connected) {
            alert('먼저 연결하세요.');
            return;
        }
        const roomId = document.getElementById('roomId').value;
        client.publish({
            destination: '/app/chat/' + roomId,
            body: JSON.stringify({
                sender: document.getElementById('sender').value,
                message: document.getElementById('message').value
            })
        });
        document.getElementById('message').value = '';
    });
</script>
</body>
</html>
