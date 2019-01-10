/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("hrCompanyService", function ($http) {

    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../hr/company/query");
    }

    this.save = function (hrCompany) {
        return $http.post("../hr/company/save", hrCompany);
    }
});