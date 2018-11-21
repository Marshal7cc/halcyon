/**
 * @class: mcap核心工具类
 * @author:Marshal.Yuan
 * @description:mcap基础js
 */

/**
 * ***********************
 * mcap-app
 * description:angularJS模块声明
 * ***********************
 */
var app=angular.module("mcap",['pagination','wui.date']);

/**
 * ***********************
 * mcap-baseContextPath
 * description:系统路径
 * ***********************
 */
var baseContextPath = getContextPath();

function getContextPath(){
    var currentPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var index=currentPath.indexOf(pathName);
    var hostName=currentPath.substring(0,index);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(hostName+projectName);
}

/**
 * ***********************
 * mcap-baseContextPathWithoutHttp
 * description:系统路径（不包括http协议名称,用于其他协议如websocket）
 * ***********************
 */
var baseContextPathWithoutHttp = getContextPathWithoutHttp();

function getContextPathWithoutHttp(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var currentPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var index=currentPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var hostName=currentPath.substring(0,index);
    var hostNameWithoutHttp = hostName.substring(7);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(hostNameWithoutHttp+projectName);
}
