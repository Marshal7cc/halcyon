//控制层
app.controller("oauthClientDetailsController", function ($scope, $controller, oauthClientDetailsService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.oauthClientDetails = {};
    }
    $scope.initForUpdate = function (id) {
        oauthClientDetailsService.queryById(id).success(function (data) {
            $scope.oauthClientDetails = data;
        });
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        oauthClientDetailsService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.submit = function () {
        oauthClientDetailsService.submit($scope.oauthClientDetails).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    };
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    };

    function deleteRows() {
        oauthClientDetailsService.remove($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    $scope.oauthClientDetailsOptions = {data: []};
    $scope.getOptions = function () {
        oauthClientDetailsService.getOptions().success(function (data) {
            $scope.oauthClientDetailsOptions = {data: data};
        });
    }

    $scope.empOptions = {data: []};
    $scope.getEmpOptions = function () {
        hrEmployeeService.getEmpOptions().success(function (data) {
            $scope.empOptions = {data: data};
        });
    }
});