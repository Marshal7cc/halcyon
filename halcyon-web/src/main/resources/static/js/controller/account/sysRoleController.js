//控制层
app.controller("sysRoleController", function ($scope, $controller, sysRoleService, hrEmployeeService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.sysRole = {};
    }
    $scope.initForUpdate = function (id) {
        sysRoleService.queryById(id).success(function (data) {
            $scope.sysRole = data;
        });
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        sysRoleService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        sysRoleService.save($scope.sysRole).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        sysRoleService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

});