/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("hrPositionService", function ($http) {
    //base crud
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../hr/position/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    }

    this.save = function (hrPosition) {
        return $http.post("../hr/position/submit", hrPosition);
    }

    this.delete = function (selectedIds) {
        return $http.get("../hr/position/remove?selectedIds=" + selectedIds);
    }

    this.queryById = function (id) {
        return $http.get("../hr/position/selectByPositionId?position=" + id);
    }

});