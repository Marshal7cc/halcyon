//控制层
app.controller("taskController", function ($scope, $controller, taskService, leaveBillService) {
    //继承
    $controller("baseController", {$scope: $scope});

    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        taskService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.auditTaskId;
    $scope.queryTaskDetail = function (taskId) {
        $scope.auditTaskId = taskId;
        taskService.queryTaskDetail(taskId).success(function (responseData) {
            $scope.task = responseData;
            $("#businessFormData").attr("th:replace", responseData.formKey);

            // $("#includeFrame").attr("src", baseContextPath + "/" + responseData.formKey);
            $scope.actBizLeave = {};
            $scope.hisRows = responseData.historicTaskList;

            leaveBillService.queryById(responseData.processInstance.businessKey).success(function (responseData) {
                $scope.actBizLeave = responseData;
            });
        });
    };
    $scope.auditResult = {};
    $scope.approve = function () {
        $scope.auditResult.action = "approve";
        taskService.approve($scope.auditTaskId, $scope.auditResult).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

});