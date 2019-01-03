/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("sysFunctionService",function($http){
    //base crud
    this.query=function(pageNum,pageSize,condition){
        return $http.post("../sys/function/query?pageNum="+pageNum+"&pageSize="+pageSize,condition);
    }

    this.save=function (sysFunction) {
        return $http.post("../sys/function/save",sysFunction);
    }

    this.delete=function (selectedIds) {
        return $http.get("../sys/function/delete?selectedIds="+selectedIds);
    }

    this.queryById=function (id) {
        return $http.get("../sys/function/queryById?id="+id);
    }

    this.getFunctionOptions=function () {
        return $http.get("../sys/function/getFunctionOptions");
    }
});