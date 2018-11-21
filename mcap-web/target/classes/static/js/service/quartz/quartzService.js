/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("quartzService",function($http){
    /**
     * scheduler方法
     */
    this.startUp=function () {
        return $http.get("../quartz/scheduler/startup");
    }
    this.standBy=function () {
        return $http.get("../quartz/scheduler/standby");
    }


    /**
     *job方法
     */
    this.query=function(pageNum,pageSize,condition){
        return $http.post("../quartz/jobDetail/query?pageNum="+pageNum+"&pageSize="+pageSize,condition);
    }

    this.pauseJob=function (jobName,jobGroup) {
        return $http.get("../quartz/job/pauseJob?jobName="+jobName+"&jobGroup="+jobGroup);
    }

    this.resumeJob=function (jobName,jobGroup) {
        return $http.get("../quartz/job/resumeJob?jobName="+jobName+"&jobGroup="+jobGroup);
    }

    this.createJob=function (jobCreateInfo) {
        return $http.post("../quartz/job/createJob",jobCreateInfo);
    }

    this.deleteJob=function (jobName,jobGroup) {
        return $http.get("../quartz/job/deleteJob?jobName="+jobName+"&jobGroup="+jobGroup);
    }
});