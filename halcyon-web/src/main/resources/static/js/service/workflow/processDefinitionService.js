/**
 * author:Marshal
 * description:service层,与后端交互
 */
app.service("processDefinitionService", function ($http) {

    this.query = function (pageNum, pageSize, condition) {
        return $http.get(baseContextPath + "/repository/process-definitions?start=" + (pageNum - 1) * pageSize + "&size=" + pageSize);
    };

    this.remove = function (processDefinitionRequest) {
        return $http.post(baseContextPath + "/activiti/repository/processDefinitions", processDefinitionRequest);
    };

});