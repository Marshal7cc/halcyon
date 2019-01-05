/**
 * author:Marshal
 * description:service层,与数据库交互
 */
app.service("mailService", function ($http) {
    /**
     * 发送邮件
     * @param mailRequest
     * @returns {*|void}
     */
    this.send = function (mailRequest) {
        return $http.post(baseContextPath + "/mail/sendMail", mailRequest);
    }

});