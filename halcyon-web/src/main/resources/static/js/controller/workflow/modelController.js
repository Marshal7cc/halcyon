//控制层
app.controller("modelController", function ($scope, $controller, modelService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.modelRequest = {};
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        modelService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        $scope.modelRequest.version = 1;
        $scope.modelRequest.metaInfo = JSON.stringify({
            name: $scope.modelRequest.name,
            version: $scope.modelRequest.version,
            description: $scope.description
        });
        modelService.save($scope.modelRequest).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    //删除
    $scope.delete = function (modelId) {
        swal({
                title: "",
                text: "确定删除？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消"
            },
            function (isConfirm) {
                if (isConfirm) {
                    modelService.delete(modelId).success(function (responseData) {
                        $scope.parseResponse(responseData);
                    });
                }
            });
    };

    //编辑模型
    $scope.edit = function (modelId) {
        var url = "../modeler.html?modelId=" + modelId;
        window.open(url);
    };

    //提取description
    $scope.descriptionRender = function (metaInfo) {
        var metaJson = JSON.parse(metaInfo);
        return metaJson.description;
    };

    $scope.deploy = function (modelId) {
        modelService.deploy(modelId).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.export = function (modelId) {
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", baseContextPath + "/workflow/repository/model/" + modelId + "/export?type=bpmn20");
        $("body").append(form);
        form.submit();
        form.remove();
    };
});