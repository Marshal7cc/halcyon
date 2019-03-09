//控制层
app.controller("oauthAccessTokenController", function ($scope, $controller, oauthAccessTokenService) {
    //继承
    $controller("baseController", {$scope: $scope});

    //初始化
    $scope.initForAdd = function () {
        $scope.oauthAccessToken = {};
        $scope.getEmpOptions();
    }
    $scope.initForUpdate = function (id) {
        oauthAccessTokenService.queryById(id).success(function (data) {
            $scope.oauthAccessToken = data;
        });
        $scope.getEmpOptions();
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        oauthAccessTokenService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save = function () {
        oauthAccessTokenService.save($scope.oauthAccessToken).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }

    function deleteRows() {
        oauthAccessTokenService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    //获取下拉框数据
    //防止ajax异步导致为空，声明一个空的
    $scope.oauthAccessTokenOptions = {data: []};
    $scope.getOptions = function () {
        oauthAccessTokenService.getOptions().success(function (data) {
            $scope.oauthAccessTokenOptions = {data: data};
        });
    }

});