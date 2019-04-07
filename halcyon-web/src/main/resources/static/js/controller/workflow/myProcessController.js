//控制层
app.controller("myProcessController", function ($scope, $controller, processMonitorService) {
    //继承
    $controller("baseController", {$scope: $scope});

    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        $scope.condition.started = "Y";
        processMonitorService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    //明细
    $scope.instanceDetail = function (processInstanceId) {
        processMonitorService.instanceDetail(processInstanceId).success(function (responseData) {
            $scope.processInstanceDetail = responseData;
            $scope.hisRows = responseData.historicTaskList;

            $("#businessFormData").attr("src", baseContextPath + "/" + responseData.formUrl);

        });

        $("#processDetailImg").attr("src", baseContextPath + "/workflow/repository/process-definitions/" + processInstanceId + "/image");
    };

});