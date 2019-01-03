//控制层
app.controller("jobRunningInfoController",function ($scope,$controller,jobRunningInfoService) {
    //继承
    $controller("baseController",{$scope:$scope});

    //crud
    //查询,初始化查询条件为空
    $scope.condition={};
    $scope.query=function (pageNum,pageSize) {
        jobRunningInfoService.query(pageNum,pageSize,$scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
});