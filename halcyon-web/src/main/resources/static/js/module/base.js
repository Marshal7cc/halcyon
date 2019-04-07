/**
 * @auth: Marshal
 * @desc: halcyon base.js
 */

//模块声明
var app = angular.module("halcyon", ['pagination', 'wui.date']);

//定义过滤器
app.filter("trustHTML", ['$sce', function ($sce) {
    return function (data) {
        return $sce.trustAsHtml(data);
    }
}]);

var baseContextPath = getContextPath();
var baseContextPathWithoutHttp = getContextPathWithoutHttp();

//得到系统路径（包括协议名）
function getContextPath() {
    var currentPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var index = currentPath.indexOf(pathName);
    var hostName = currentPath.substring(0, index);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (hostName + projectName);
}

//得到系统路径（不包括协议名）
function getContextPathWithoutHttp() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var currentPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var index = currentPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var hostName = currentPath.substring(0, index);
    var hostNameWithoutHttp = hostName.substring(7);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (hostNameWithoutHttp + projectName);
}



