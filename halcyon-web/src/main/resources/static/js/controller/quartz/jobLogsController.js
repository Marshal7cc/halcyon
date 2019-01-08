//控制层
app.controller("jobLogsController", function ($scope, $controller, jobLogsService) {
    //继承
    $controller("baseController",{$scope:$scope});

    //crud
    //查询,初始化查询条件为空
    $scope.condition={};
    $scope.query=function (pageNum,pageSize) {
        jobLogsService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
});