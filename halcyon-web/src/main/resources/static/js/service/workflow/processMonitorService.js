/**
 * author:Marshal
 * description:工作流模型service
 */
app.service("processMonitorService", function ($http) {

    this.query = function (pageNum, pageSize, condition) {
        var url = baseContextPath + "/workflow/process-instances/monitor/query?start=" + (pageNum - 1) * pageSize + "&size=" + pageSize;
        return $http.post(url, condition);
    };

    this.instanceDetail = function (processInstanceId) {
        return $http.get(baseContextPath + "/workflow/processInstance/" + processInstanceId);
    };

    this.active = function (processInstanceId) {
        return $http.get(baseContextPath + "/workflow/runtime/prc/active/" + processInstanceId);
    };

    this.suspend = function (processInstanceId) {
        return $http.get(baseContextPath + "/workflow/runtime/prc/suspend/" + processInstanceId);
    };

    this.end = function (processInstanceId) {
        return $http.get(baseContextPath + "/workflow/runtime/prc/end/" + processInstanceId);
    };
});