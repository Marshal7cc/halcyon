/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("sysUserService",function($http){
    //base crud
    this.query=function(pageNum,pageSize,condition){
        return $http.post("../account/user/query?pageNum="+pageNum+"&pageSize="+pageSize,condition);
    }

    this.save=function (sysUser) {
        return $http.post("../account/user/save",sysUser);
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