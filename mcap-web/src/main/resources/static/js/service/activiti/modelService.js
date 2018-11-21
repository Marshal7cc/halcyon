/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("modelService",function($http){
    //base crud
    this.query=function(pageNum,pageSize,condition){
        return $http.get(baseContextPath+"/repository/models?start="+(pageNum-1)*pageSize+"&size="+pageSize);
    }

    this.save=function (modelRequest) {
        return $http.post("../activiti/repository/models",modelRequest);
    }

    this.delete=function (selectedIds) {
        return $http.get("../account/user/delete?selectedIds="+selectedIds);
    }

    this.queryById=function (id) {
        return $http.get("../account/user/queryById?id="+id);
    }

    //other opertions
    this.getOptions=function(){
        return $http.get("../account/user/getOptions");
    }
});