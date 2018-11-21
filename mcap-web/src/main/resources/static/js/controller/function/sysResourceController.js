//控制层
app.controller("sysResourceController", function ($scope, $controller, sysResourceService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.sysResource = {};
    }
    $scope.initForUpdate = function (id) {
        sysResourceService.queryById(id).success(function (data) {
            $scope.sysResource = data;
        });
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        sysResourceService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        sysResourceService.save($scope.sysResource).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //批量删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }
    function deleteRows(){
        sysResourceService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
});