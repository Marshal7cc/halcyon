/**
 * author:Marshal
 * description:service层
 */
app.service("sysAttachmentCategoryService", function ($http) {
    //base crud
    this.query = function (pageNum, pageSize, condition) {
        return $http.post("../sys/attachment/category/query?pageNum=" + pageNum + "&pageSize=" + pageSize, condition);
    };

    this.save = function (sysAttachmentCategory) {
        return $http.post("../sys/attachment/category/save", sysAttachmentCategory);
    }

    this.delete = function (selectedIds) {
        return $http.get("../sys/attachment/category/delete?selectedIds=" + selectedIds);
    }

    this.queryById = function (id) {
        return $http.get("../sys/attachment/category/queryById?id=" + id);
    }

});