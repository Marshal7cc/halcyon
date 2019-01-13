//控制层
app.controller("hrUnitController", function ($scope, $controller, hrUnitService, hrPositionService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.hrUnit = {};
        $scope.getParentUnitOptions();
    }
    $scope.initForUpdate = function (id) {
        hrUnitService.queryById(id).success(function (data) {
            $scope.hrUnit = data;
        });
        $scope.getParentUnitOptions();
        $scope.getManagerPositionOptions(id);
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        hrUnitService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        hrUnitService.save($scope.hrUnit).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        hrUnitService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    $scope.parentUnitOptions = {data: []};
    $scope.getParentUnitOptions = function () {
        hrUnitService.getParentUnitOptions().success(function (data) {
            $scope.parentUnitOptions = {data: data};
        });
    }

    $scope.managerPositionOptions = {data: []};
    $scope.getManagerPositionOptions = function (unitId) {
        hrPositionService.selectByUnitId(unitId).success(function (data) {
            $scope.managerPositionOptions = {data: data};
        });
    }
});