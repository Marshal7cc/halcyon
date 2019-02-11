/**
 * author:Marshal
 * description:service层,与后端交互
 */
app.service("leaveBillService", function ($http) {

    this.createLeaveBillProcessInstance = function (actBizLeave) {
        return $http.post(baseContextPath + "/workflow/leaveBill/createNewInstance", actBizLeave);
    };

    this.queryById = function (id) {
        return $http.post(baseContextPath + "/workflow/leaveBill/queryById/" + id);
    };

});