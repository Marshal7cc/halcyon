//控制层
app.controller("mailController", function ($scope, $controller, mailService) {
    //继承
    $controller("baseController", {$scope: $scope});

    $scope.initForAdd = function () {
        $scope.mailRequest = {};
    }

    //发送
    $scope.send = function () {
        mailService.send($scope.mailRequest).success(function (responseData) {
            if (responseData.success) {
                swal("", "发送成功", "success");
            } else {
                swal("", "发送失败", "success");
            }
        });
    }

});