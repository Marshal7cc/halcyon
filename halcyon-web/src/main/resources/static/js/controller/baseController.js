/**
 * author:Marshal
 * description:halcyon基础controller
 */
app.controller("baseController", function ($scope) {
    /**
     * @author:Marshal
     * @description:分页
     */
    $scope.pageConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };
    $scope.reloadList = function () {
        $scope.selectedIds = [];
        $scope.query($scope.pageConf.currentPage, $scope.pageConf.itemsPerPage);
    };

    /**
     * @author:Marshal
     * @description:获取选中记录ID
     */
    $scope.selectedIds = [];
    $scope.updateSelected = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectedIds.push(id);
        } else {
            var index = $scope.selectedIds.indexOf(id);
            $scope.selectedIds.splice(index, 1);
        }
    }

    /**
     * @author:Marshal
     * @description:解析ResponseData渲染到前端
     */
    $scope.parseResponse = function (responseData) {
        if (responseData.success) {
            if (responseData.rows) {
                $scope.rows = responseData.rows;
                $scope.pageConf.totalItems = responseData.total;
                $(".overlay").hide();
            } else {
                if (responseData.message) {
                    swal("", responseData.message, "success");
                    $scope.reloadList();
                } else {
                    swal("", "操作成功", "success");
                }
            }
        } else {
            swal("", responseData.message, "info");
        }
    }

    /**
     * @author:Marshal
     * @description:删除确认
     */
    $scope.deleteConfirm = function (deleteRows) {
        debugger
        if ($scope.selectedIds.length == 0) {
            swal("", "未选择数据!", "info");
            return;
        }
        swal({
                title: "",
                text: "确定删除？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消"
            },
            function (isConfirm) {
                if (isConfirm) {
                    window[deleteRows()].call();
                }
            });
    }


    //格式化时间
    $scope.formatDateTime = function (item) {
        if (item) {
            var date = new Date(item);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            minute = minute < 10 ? ('0' + minute) : minute;
            var second = date.getSeconds();
            second = second < 10 ? ('0' + second) : second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
        } else {
            return "";
        }
    }

});