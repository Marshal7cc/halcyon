/**
 * author:Marshal
 * description:service层
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