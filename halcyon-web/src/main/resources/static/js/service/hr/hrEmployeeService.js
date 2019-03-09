/**
 * author:Marshal
 * description:service层
 */
app.service("hrEmployeeService", function ($http) {
    //base crud
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../hr/employee/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    }

    this.save = function (hrEmployee) {
        return $http.post("../hr/employee/submit", hrEmployee);
    }

    this.delete = function (selectedIds) {
        return $http.get("../hr/employee/remove?selectedIds=" + selectedIds);
    }

    this.queryById = function (id) {
        return $http.get("../hr/employee/selectByEmployeeId?employeeId=" + id);
    }

    this.getEmpOptions = function () {
        return $http.get("../hr/employee/getEmpOptions");
    }
});