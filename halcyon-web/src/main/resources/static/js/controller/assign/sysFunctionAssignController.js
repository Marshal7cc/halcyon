//控制层
app.controller("sysFunctionAssignController", function ($scope, $controller, sysFunctionAssignService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        sysFunctionAssignService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.save = function () {
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getCheckedNodes();
        console.log(nodes);
        sysFunctionAssignService.functionAssign(nodes).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
        ;
    }

    $scope.selectRoleFunctionAssignList = function (roleId) {
        sysFunctionAssignService.selectRoleFunctionAssignList(roleId).success(function (responseData) {
            var zNodes = responseData;
            var setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                }
            ;
            $.fn.zTree.init($("#tree"), setting, zNodes);
            setCheck();
            $("#py").bind("change", setCheck);
            $("#sy").bind("change", setCheck);
            $("#pn").bind("change", setCheck);
            $("#sn").bind("change", setCheck);
        });
    };


    //ztree

    var code;

    function setCheck() {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            py = $("#py").attr("checked") ? "p" : "",
            sy = $("#sy").attr("checked") ? "s" : "",
            pn = $("#pn").attr("checked") ? "p" : "",
            sn = $("#sn").attr("checked") ? "s" : "",
            type = {"Y": py + sy, "N": pn + sn};
        zTree.setting.check.chkboxType = type;
        showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
    }

    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>" + str + "</li>");
    }

});