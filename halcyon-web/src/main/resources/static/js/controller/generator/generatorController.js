//控制层
app.controller("generatorController", function ($scope, $controller, generatorService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.init = function () {
        $scope.allTables();
    }


    //保存
    $scope.generatorInfo={};
    $scope.generate = function () {
        generatorService.generate($scope.generatorInfo).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    // $scope.allTables = {data: []};
    $scope.allTables = function () {
        generatorService.allTables().success(function (response) {
            $scope.allTables = {data: response.rows};
        });
    };

});