/**
 * author:Marshal
 * description:service层
 */
app.service("hrUnitService", function ($http) {
    //base crud
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../hr/unit/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    }

    this.save = function (hrUnit) {
        return $http.post("../hr/unit/submit", hrUnit);
    }

    this.delete = function (selectedIds) {
        return $http.get("../hr/unit/remove?selectedIds=" + selectedIds);
    }

    this.queryById = function (id) {
        return $http.get("../hr/unit/selectByUnitId?unitId=" + id);
    }

    this.getParentUnitOptions = function () {
        return $http.get("../hr/unit/getParentUnitOptions");
    }
});