//控制层
app.controller("processMonitorController", function ($scope, $controller, processMonitorService, hrEmployeeService, taskService) {
    //继承
    $controller("baseController", {$scope: $scope});

    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
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

    $scope.active = function (processInstanceId) {
        processMonitorService.active(processInstanceId).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.suspend = function (processInstanceId) {
        processMonitorService.suspend(processInstanceId).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.end = function (processInstanceId) {
        processMonitorService.end(processInstanceId).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.taskActionRequest = {};
    $scope.selectDelegateAssign = function (taskId) {
        $scope.taskActionRequest.currentTaskId = taskId;
        $scope.getEmpCodeOptions();
    };

    $scope.delegate = function () {
        $scope.taskActionRequest.action = "delegate";
        taskService.adminHandle($scope.taskActionRequest.currentTaskId, $scope.taskActionRequest).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.empCodeOptions = {data: []};
    $scope.getEmpCodeOptions = function () {
        hrEmployeeService.getEmpCodeOptions().success(function (data) {
            $scope.empCodeOptions = {data: data};
        });
    }

});