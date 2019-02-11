/**
 * author:Marshal
 * description:service层,与后端交互
 */
app.service("processDefinitionService", function ($http) {

    this.query = function (pageNum, pageSize, condition) {
        return $http.get(baseContextPath + "/repository/process-definitions?start=" + (pageNum - 1) * pageSize + "&size=" + pageSize);
    };

    this.delete = function (deploymentId) {
        return $http.post(baseContextPath + "/workflow/repository/deployments/" + deploymentId + "/delete");
    };

    this.processDefinitionDetail = function (processDefinitionId) {
        return $http.get(baseContextPath + "/workflow/repository/process-definitions/" + processDefinitionId);
    };

    this.deploymentDetail = function (deploymentId) {
        return $http.get(baseContextPath + "/workflow/repository/deployments/" + deploymentId);
    }

});