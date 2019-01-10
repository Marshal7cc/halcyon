//控制层
app.controller("hrPositionController", function ($scope, $controller, hrPositionService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.hrPosition = {};
    }
    $scope.initForUpdate = function (id) {
        hrPositionService.queryById(id).success(function (data) {
            $scope.hrPosition = data;
        });
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        hrPositionService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        hrPositionService.save($scope.hrPosition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        hrPositionService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    $scope.hrPositionOptions = {data: []};
    $scope.getOptions = function () {
        hrPositionService.getOptions().success(function (data) {
            $scope.hrPositionOptions = {data: data};
        });
    }
});