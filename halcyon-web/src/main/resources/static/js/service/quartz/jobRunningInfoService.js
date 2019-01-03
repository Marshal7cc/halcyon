/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("jobRunningInfoService",function($http){
    //base crud
    this.query=function(pageNum,pageSize,condition){
        return $http.post("../quartz/jobRunningInfo/query?pageNum="+pageNum+"&pageSize="+pageSize,condition);
    }
});