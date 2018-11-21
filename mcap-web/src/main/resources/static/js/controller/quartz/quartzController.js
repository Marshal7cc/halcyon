//控制层
app.controller("quartzController", function ($scope, $controller, quartzService) {
    //继承
    $controller("baseController", {$scope: $scope});
    /**
     * scheduler方法
     */
    $scope.startUp = function () {
        quartzService.startUp().success(function (responseData) {
            $scope.parseResponse(responseData);
        })
    }
    $scope.standBy = function () {
        quartzService.standBy().success(function (responseData) {
            $scope.parseResponse(responseData);
        })
    }
    /**
     * job方法
     */
    $scope.condition = {};
    $scope.query = function (pageNum, pageSize) {
        quartzService.query(pageNum, pageSize, $scope.condition).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    $scope.pauseJob = function (jobName, jobGroup) {
        quartzService.pauseJob(jobName, jobGroup).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    $scope.resumeJob = function (jobName, jobGroup) {
        quartzService.resumeJob(jobName, jobGroup).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }
    $scope.deleteJob = function (jobName, jobGroup) {
        if($scope.deleteConfirm()==true){
            quartzService.deleteJob(jobName, jobGroup).success(function (responseData) {
                $scope.parseResponse(responseData);
            });
        }
    }
    //保存
    $scope.createJob = function () {
        quartzService.createJob($scope.jobCreateInfo).success(function (responseData) {
            $scope.parseResponse(responseData);
        });
    }

    $scope.initForAdd = function () {
        $scope.jobCreateInfo = {jobData: []};
    }
    //新增行表
    $scope.addLine = function () {
        $scope.jobCreateInfo.jobData.push({});
    }
    //删除行表
    $scope.deleteLine = function (index) {
        $scope.jobCreateInfo.jobData.splice(index, 1);
    }
});