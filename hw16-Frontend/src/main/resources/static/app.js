var stompClient = null;

$(document).ready(connect());

$(() => {
    $("form").on('submit', event => event.preventDefault());
    $("#connect").click(connect);
    $("#disconnect").click(disconnect);
});

function setConnected(connected) {
    if (connected) {
        $("#usersListTable").show();
    } else {
        $("#usersListTable").hide();
    }
}

function connect() {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, frame => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/DBServiceResponse', DBServiceMessage =>
            showUsersList(JSON.parse(DBServiceMessage.body)));
    });
}

const showUsersList = (jsonUser) => {
    let user_data = '';
    user_data += '<tr>';
    user_data += '<td>' + jsonUser.id + '</td>';
    user_data += '<td>' + jsonUser.name + '</td>';
    user_data += '<td>' + jsonUser.age + '</td>';
    user_data += '<td>' + jsonUser.address + '</td>';
    user_data += '<td>' + jsonUser.phone + '</td>';
    user_data += '</tr>';
    $("#usersListTable").append(user_data);
};

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function saveUser() {
    let newUser = {
        name: $("#name").val(),
        age: parseInt($("#age").val()),
        address: $("#address").val(),
        phone: $("#phone").val()
    };
    stompClient.send("/app/createUserMessage", {}, JSON.stringify({'messageStr': newUser}));
}