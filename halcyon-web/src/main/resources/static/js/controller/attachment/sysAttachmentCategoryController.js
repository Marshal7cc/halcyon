//控制层
app.controller("sysAttachmentCategoryController", function ($scope, $controller, sysAttachmentCategoryService, hrEmployeeService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.sysAttachmentCategory = {};
        $scope.getEmpOptions();
    }
    $scope.initForUpdate = function (id) {
        sysAttachmentCategoryService.queryById(id).success(function (data) {
            $scope.sysAttachmentCategory = data;
        });
        $scope.getEmpOptions();
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        sysAttachmentCategoryService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        sysAttachmentCategoryService.save($scope.sysAttachmentCategory).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        sysAttachmentCategoryService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

});