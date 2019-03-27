package com.marshal.halcyon.base.hr.controller;

import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.base.hr.entity.HrEmployee;
import com.marshal.halcyon.base.hr.service.HrEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/1/9
 * @desc:
 */
@RestController
@RequestMapping("/hr/employee")
public class HrEmployeeController extends BaseController {

    @Autowired
    HrEmployeeService hrEmployeeService;

    /**
     * 员工信息查询
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/query")
    public ResponseData query(@RequestBody HrEmployee condition, int pageNum, int pageSize) {
        List<HrEmployee> list = hrEmployeeService.select(condition, pageNum, pageSize);
        return new ResponseData(list);
    }

    /**
     * 部门信息提交
     *
     * @param hrEmployee
     * @return
     */
    @RequestMapping("/submit")
    public ResponseData submit(@RequestBody HrEmployee hrEmployee) {
        if (!getValidator().hasError(hrEmployee)) {
            return new ResponseData(false, getValidator().getErrors(hrEmployee));
        }
        hrEmployeeService.save(hrEmployee);
        return new ResponseData(true, "保存成功");
    }

    /**
     * 部门信息删除
     *
     * @param selectedIds
     * @return
     */
    @RequestMapping("/remove")
    public ResponseData remove(@RequestParam("selectedIds") Long[] selectedIds) {
        hrEmployeeService.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    @RequestMapping("/selectByEmployeeId")
    public HrEmployee selectByEmployeeId(@RequestParam Long employeeId) {
        return hrEmployeeService.selectByPrimaryKey(employeeId);
    }

    @RequestMapping("/getEmpOptions")
    public List<Map> getEmpOptions() {
        return hrEmployeeService.getEmpOptions();
    }
}
