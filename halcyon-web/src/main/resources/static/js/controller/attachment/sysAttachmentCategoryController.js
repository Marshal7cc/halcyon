//控制层
app.controller("sysUserController", function ($scope, $controller, sysUserService, hrEmployeeService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.sysUser = {};
        $scope.getEmpOptions();
    }
    $scope.initForUpdate = function (id) {
        sysUserService.queryById(id).success(function (data) {
            $scope.sysUser = data;
        });
        $scope.getEmpOptions();
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        sysUserService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        sysUserService.save($scope.sysUser).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        sysUserService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    $scope.sysUserOptions = {data: []};
    $scope.getOptions = function () {
        sysUserService.getOptions().success(function (data) {
            $scope.sysUserOptions = {data: data};
        });
    }

    $scope.empOptions = {data: []};
    $scope.getEmpOptions = function () {
        hrEmployeeService.getEmpOptions().success(function (data) {
            $scope.empOptions = {data: data};
        });
    }
});