/**
 * author:Marshal
 * description:service层
 */
app.service("jobLogsService", function ($http) {
    //base crud
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../quartz/jobLogs/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    }
});