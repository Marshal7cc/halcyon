/**
 * author:Marshal
 * description:service层
 */
app.service("oauthAccessTokenService", function ($http) {
    //base crud
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../oauth2/accessToken/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    };

    this.save = function (oauthAccessToken) {
        return $http.post("../oauth2/accessToken/submit", oauthAccessToken);
    };

    this.delete = function (selectedIds) {
        return $http.get("../oauth2/accessToken/remove?selectedIds=" + selectedIds);
    };

    this.queryById = function (id) {
        return $http.get("../oauth2/accessToken/queryById?id=" + id);
    }

});