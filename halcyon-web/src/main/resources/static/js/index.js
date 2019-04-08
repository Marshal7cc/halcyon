/**
 * index.js
 * @author:Marshal
 */
$.ajax({
    type: "GET",
    url: "sys/function/getMenus",
    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
    dataType: "text",
    success: function (responseData) {
        var funtionTree = JSON.parse(responseData);
        var menuHtml = '';
        menuHtml += parseToMenu(funtionTree);
        $('.sidebar-menu').append(menuHtml);
    },
    error: function (e) {
        console.log(e);
    }
});

function parseToMenu(functionTree) {
    var menuHtml = "";
    functionTree.forEach(function (treeNode) {
        if (treeNode.childFunctions == undefined || treeNode.childFunctions == null) {
            menuHtml += '<li onclick="jumpToFunction(\'' + treeNode.functionName + '\',\'' + treeNode.url + '\')">\n' +
                '    <a><i class="' + treeNode.functionIcon + '"></i> <span>' + treeNode.functionName + '</span></a>\n' +
                '</li>';
        } else if (treeNode.childFunctions != undefined) {
            menuHtml += '<li class="treeview">\n' +
                '            <a href="#"><i class="' + treeNode.functionIcon + '"></i> <span>' + treeNode.functionName + '</span>\n' +
                '                <span class="pull-right-container">\n' +
                '                            <i class="fa fa-angle-left pull-right"></i>\n' +
                '                        </span>\n' +
                '            </a>\n' +
                '            <ul class="treeview-menu">';
            menuHtml += parseToMenu(treeNode.childFunctions);
            menuHtml += '</ul>\n' +
                '        </li>';
        }
    });
    return menuHtml;
}

function jumpToFunction(functionName, functionUrl) {
    $('#bread-function').text(functionName);
    $('#bread-function').css('display', 'inline');
    $('#mainframe').attr('src', functionUrl);
}

function backToIndex() {
    $('#bread-function').text('');
    $('#bread-function').css('display', 'none');
    $('#mainframe').attr('src', 'dashboard.html');
}

function refreshFunction() {
    var mainframe = window.document.getElementById('mainframe');
    mainframe.contentWindow.location.reload(true);
}