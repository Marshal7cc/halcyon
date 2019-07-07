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
            $scope.hisRows = responseData.historicTaskList;

            var url = baseContextPath + "/" + responseData.formKey + "?businessKey=" + responseData.processInstance.businessKey;
            $("#businessFormData").attr("src", url);

        });
    };
    $scope.taskActionRequest = {};
    $scope.approve = function () {
        $scope.taskActionRequest.action = "complete";
        $scope.taskActionRequest.approveResult = "approve";
        taskService.handle($scope.auditTaskId, $scope.taskActionRequest).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.reject = function () {
        $scope.taskActionRequest.action = "complete";
        $scope.taskActionRequest.approveResult = "reject";
        taskService.handle($scope.auditTaskId, $scope.taskActionRequest).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.renderAction = function (action) {
        var apprvText = "";
        if (action == 'approve') {
            apprvText = "同意";
        } else if (action == 'reject') {
            apprvText = "拒绝";
        } else if (action == 'addSign') {
            apprvText = "加签";
        } else if (action == 'delegate') {
            apprvText = "转交";
        } else if (action == 'jump') {
            apprvText = "跳转";
        } else if (action == 'recall') {
            apprvText = "撤回";
        } else if (action == "autoDelegate") {
            apprvText = "自动转交";
        } else {
            apprvText = action || ''
        }
        return apprvText;
    }

});