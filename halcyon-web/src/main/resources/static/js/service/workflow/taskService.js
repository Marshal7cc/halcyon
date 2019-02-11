/**
 * author:Marshal
 * description:service层,与后端交互
 */
app.service("taskService", function ($http) {

    this.query = function (pageNum, pageSize, condition) {
        return $http.post(baseContextPath + "/workflow/tasks/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    };

    this.queryTaskDetail = function (taskId) {
        return $http.post(baseContextPath + "/workflow/tasks/" + taskId + "/details");
    };

    this.approve = function (taskId, auditResult) {
        return $http.post(baseContextPath + "/workflow/task/handle/" + taskId, auditResult);
    };

});