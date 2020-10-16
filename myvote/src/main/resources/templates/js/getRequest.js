//返回?后的json对象
function getRequest() {
    //获取url中”?“后的值
    var url = location.search;
    var newRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            newRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return newRequest;
}