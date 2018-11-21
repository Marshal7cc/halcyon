/**
 * author:Marshal
 * description:mcap基础controller
 */
app.controller("baseController", function ($scope) {
    /**
     * @author:Marshal
     * @description:分页
     */
    $scope.pageConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 8,
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
            if (responseData.rows != null && responseData.rows != undefined) {
                $scope.rows = responseData.rows;
                $scope.pageConf.totalItems = responseData.total;
                $(".overlay").hide();
            } else {
                if (responseData.message != null && responseData.message != undefined) {
                    swal("", responseData.message, "success");
                    $scope.reloadList();
                }
            }
        } else {
            swal("", responseData.message, "info");
            $scope.reloadList();
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

});