/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("sysResourceService",function($http){
    //base crud
    this.query=function(pageNum,pageSize,condition){
        return $http.post("../sys/resource/query?pageNum="+pageNum+"&pageSize="+pageSize,condition);
    }

    this.save=function (sysResource) {
        return $http.post("../sys/resource/save",sysResource);
    }

    this.delete=function (selectedIds) {
        return $http.get("../sys/resource/delete?selectedIds="+selectedIds);
    }

    this.queryById=function (id) {
        return $http.get("../sys/resource/queryById?id="+id);
    }

    //other opertions
    this.getResourceOptions=function(){
        return $http.get("../sys/resource/getResourceOptions");
    }
});