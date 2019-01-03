//控制层
app.controller("modelController",function ($scope,$controller,modelService) {
    //继承
    $controller("baseController",{$scope:$scope});

    //初始化
    $scope.initForAdd=function(){
        $scope.model={};
    }

    //crud
    //查询,初始化查询条件为空
    $scope.condition={};
    $scope.query=function (pageNum,pageSize) {
        modelService.query(pageNum,pageSize,$scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //保存
    $scope.save=function () {
        $scope.modelRequest.version=1;
        $scope.modelRequest.metaInfo=JSON.stringify({
            name:$scope.modelRequest.name,
            version:$scope.modelRequest.version,
            description:$scope.description
        });
        modelService.save($scope.modelRequest).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //批量删除
    $scope.delete = function () {
        $scope.deleteConfirm(deleteRows);
    }
    function deleteRows(){
        modelService.delete($scope.selectedIds).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    //编辑模型
    $scope.edit=function (modelId) {
        var url = "../modeler.html?modelId="+modelId;
        window.open(url);
    }
    //提取description
    $scope.descriptionRender=function (metaInfo) {
        var metaJson = JSON.parse(metaInfo);
        return metaJson.description;
    }
});