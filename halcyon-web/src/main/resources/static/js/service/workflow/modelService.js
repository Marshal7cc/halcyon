/**
 * author:Marshal
 * description:工作流模型service
 */
app.service("modelService", function ($http) {

    this.query = function (pageNum, pageSize, condition) {
        return $http.get(baseContextPath + "/repository/models?start=" + (pageNum - 1) * pageSize + "&size=" + pageSize);
    };

    this.save = function (modelRequest) {
        return $http.post(baseContextPath + "/workflow/repository/models", modelRequest);
    };

    this.delete = function (modelId) {
        return $http.get(baseContextPath + "/workflow/repository/model/" + modelId + "/delete");
    };

    this.deploy = function (modelId) {
        return $http.get(baseContextPath + "/workflow/repository/model/" + modelId + "/deploy");
    };

    this.export = function (modelId) {
        return $http.get(baseContextPath + "/workflow/repository/model/" + modelId + "/export?type=bpmn20");
    }

});