/**
 * author:Marshal
 * description:service层
 */
app.service("oauthClientDetailsService", function ($http) {
    //base crud
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../oauth2/clients/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    }

    this.save = function (oauthClientDetails) {
        return $http.post("../oauth2/clients/submit", oauthClientDetails);
    }

    this.delete = function (selectedIds) {
        return $http.get("../oauth2/clients/remove?selectedIds=" + selectedIds);
    }

    this.queryById = function (id) {
        return $http.get("../oauth2/clients/queryById?id=" + id);
    }
});