//控制层
app.controller("hrCompanyController", function ($scope, $controller, hrCompanyService) {
    //继承
    $controller("baseController", {$scope: $scope});

    // $scope.hrCompany = {};
    $scope.query = function () {
        hrCompanyService.query().success(function (responseData) {
            if (responseData.length > 0) {
                $scope.hrCompany = responseData[0];
            } else {
                $scope.hrCompany = {};
            }
        });
    }

    $scope.save = function () {
        hrCompanyService.save($scope.hrCompany).success(function (responseData) {
            $scope.parseResponse(responseData);
            $scope.query();
        });
    }

});