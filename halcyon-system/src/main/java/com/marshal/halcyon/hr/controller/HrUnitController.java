package com.marshal.halcyon.hr.controller;

import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.hr.entity.HrUnit;
import com.marshal.halcyon.hr.service.HrUnitService;
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
        List<HrUnit> list = hrUnitService.select(condition, pageNum, pageSize);
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
        hrUnitService.save(hrUnit);
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
        hrUnitService.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    /**
     * 根据部门id查询部门
     *
     * @param unitId
     * @return
     */
    @RequestMapping("/selectByUnitId")
    public HrUnit selectByUnitId(@RequestParam Long unitId) {
        return hrUnitService.selectByPrimaryKey(unitId);
    }

    @RequestMapping("/getParentUnitOptions")
    public List<Map> getOptions() {
        return hrUnitService.getOptions();
    }
}
