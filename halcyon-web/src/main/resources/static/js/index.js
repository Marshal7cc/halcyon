$(document).ready(function () {
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
                menuHtml += '<li>\n' +
                    '    <a href="javascript:$(\'#mainframe\').attr(\'src\',\'' + treeNode.url + '\')"><i class="' + treeNode.functionIcon + '"></i> <span>' + treeNode.functionName + '</span></a>\n' +
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
});