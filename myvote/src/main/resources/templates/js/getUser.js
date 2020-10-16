$.getJSON("http://localhost:8080/vote/getUser", function (data) {
    $("#username").text(data.name);
});