/**
 * author:Marshal
 * description:service层
 */
app.service("sysRoleService", function ($http) {
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../sys/role/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    };

    this.save = function (sysRole) {
        return $http.post("../sys/role/submit", sysRole);
    };

    this.delete = function (selectedIds) {
        return $http.get("../sys/role/remove?selectedIds=" + selectedIds);
    };

    this.queryById = function (id) {
        return $http.get("../sys/role/queryById?id=" + id);
    };

    //other opertions
    this.getOptions = function () {
        return $http.get("../account/user/getOptions");
    }
});