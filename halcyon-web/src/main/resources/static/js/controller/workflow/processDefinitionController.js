//控制层
app.controller("processDefinitionController", function ($scope, $controller, processDefinitionService) {
    //继承
    $controller("baseController", {$scope: $scope});

    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        processDefinitionService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };

    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        hrEmployeeService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

});