/**
 * author:Marshal
 * description:service层
 */
app.service("sysFunctionAssignService", function ($http) {
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../sys/role/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    };

    this.selectRoleFunctionAssignList = function (roleId) {
        return $http.get("../sys/role/functionAssignList?roleId=" + roleId);
    };

    this.functionAssign = function (sysRoleFunctions) {
        return $http.post("../sys/role/functionAssign", JSON.stringify(sysRoleFunctions));
    };

});