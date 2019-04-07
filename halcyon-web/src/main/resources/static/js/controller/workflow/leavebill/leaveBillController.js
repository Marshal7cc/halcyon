//控制层
app.controller("leaveBillController", function ($scope, $controller, leaveBillService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.actBizLeave = {};
    }

    //保存
    $scope.createLeaveBillProcessInstance = function () {
        leaveBillService.createLeaveBillProcessInstance($scope.actBizLeave).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    $scope.loadFormData = function () {
        var businessKey = getQueryString('businessKey');
        debugger
        leaveBillService.queryById(businessKey).success(function (responseData) {
            debugger;
            $scope.formData = responseData;
        });
    }
});