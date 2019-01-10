package com.marshal.halcyon.hr.controller;

import com.marshal.halcyon.core.component.ResponseData;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.hr.entity.HrUnit;
import com.marshal.halcyon.hr.service.HrUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/1/9
 * @desc:
 */
@RestController
@RequestMapping("/hr/unit")
public class HrUnitController extends BaseController {

    @Autowired
    HrUnitService hrUnitService;

    /**
     * 部门信息查询
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/query")
    public ResponseData query(@RequestBody HrUnit condition, int pageNum, int pageSize) {
        List<HrUnit> list = hrUnitService.query(condition, pageNum, pageSize);
        return new ResponseData(list);
    }

    /**
     * 部门信息提交
     *
     * @param hrUnit
     * @return
     */
    @RequestMapping("/submit")
    public ResponseData submit(@RequestBody HrUnit hrUnit) {
        if (!getValidator().isValid(hrUnit)) {
            return new ResponseData(false, getValidator().getErrors(hrUnit));
        }
        hrUnitService.submit(hrUnit);
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
        hrUnitService.remove(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    @RequestMapping("/selectByUnitId")
    public HrUnit selectByUnitId(@RequestParam Long unitId) {
        return hrUnitService.selectByUnitId(unitId);
    }

    
}
