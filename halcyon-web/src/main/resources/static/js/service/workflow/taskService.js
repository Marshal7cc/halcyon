/**
 * author:Marshal
 * description:service层,与后端交互
 */
app.service("taskService", function ($http) {

    this.query = function (pageNum, pageSize, condition) {
        return $http.post(baseContextPath + "/workflow/tasks/query?start=" + (pageNum - 1) * pageSize + "&size=" + pageSize, condition);
    };

    this.queryTaskDetail = function (taskId) {
        return $http.post(baseContextPath + "/workflow/tasks/" + taskId + "/details");
    };

    this.handle = function (taskId, taskActionRequest) {
        return $http.post(baseContextPath + "/workflow/task/handle/" + taskId, taskActionRequest);
    };

    this.adminHandle = function (taskId, taskActionRequest) {
        return $http.post(baseContextPath + "/workflow/task/handle/admin/" + taskId, taskActionRequest);
    };

});