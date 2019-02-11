//控制层
app.controller("processDefinitionController", function ($scope, $controller, processDefinitionService) {
    //继承
    $controller("baseController", {$scope: $scope});

    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        processDefinitionService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    //删除
    $scope.delete = function (deploymentId) {
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
                    processDefinitionService.delete(deploymentId).success(function (responseData) {
                        $scope.parseResponse(responseData);
                    });
                }
            });
    };

    //明细
    $scope.detail = function (processDefinitionId, deploymentId) {
        processDefinitionService.processDefinitionDetail(processDefinitionId).success(function (responseData) {
            $scope.processDefinition = responseData;
        });
        processDefinitionService.deploymentDetail(deploymentId).success(function (responseData) {
            $scope.deployment = responseData;
        });
        $("#processDetailImg").attr("src", baseContextPath + "/workflow/repository/process-definitions/" + processDefinitionId + "/image");
    };

});