//控制层
app.controller("sysFunctionController", function ($scope, $controller, sysFunctionService,sysResourceService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.sysFunction = {};
    }
    $scope.initForUpdate = function (id) {
        sysFunctionService.queryById(id).success(function (data) {
            $scope.sysFunction = data;
        });
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        sysFunctionService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        sysFunctionService.save($scope.sysFunction).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //批量删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }
    function deleteRows(){
        sysFunctionService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    $scope.sysResourceOptions = {data: []};
    $scope.getResourceOptions = function () {
        sysResourceService.getResourceOptions().success(function (data) {
            $scope.sysResourceOptions = {data: data};
        });
    }

    $scope.sysFunctionOptions = {data: []};
    $scope.getFunctionOptions = function () {
        sysFunctionService.getFunctionOptions().success(function (data) {
            $scope.sysFunctionOptions = {data: data};
        });
    }
});