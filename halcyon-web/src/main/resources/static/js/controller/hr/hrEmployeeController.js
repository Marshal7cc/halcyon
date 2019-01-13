//控制层
app.controller("hrEmployeeController", function ($scope, $controller, hrEmployeeService, hrPositionService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.hrEmployee = {};
        $scope.getHrPositionOptions();

    }
    $scope.initForUpdate = function (id) {
        hrEmployeeService.queryById(id).success(function (data) {
            $scope.hrEmployee = data;
        });
        $scope.getHrPositionOptions();
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        hrEmployeeService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        hrEmployeeService.save($scope.hrEmployee).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        hrEmployeeService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    $scope.hrPositionOptions = {data: []};
    $scope.getHrPositionOptions = function () {
        hrPositionService.getParentPositionOptions().success(function (data) {
            $scope.hrPositionOptions = {data: data};
        });
    }
});