package com.marshal.halcyon.hr.controller;

import com.marshal.halcyon.core.component.ResponseData;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.hr.entity.HrPosition;
import com.marshal.halcyon.hr.service.HrPositionService;
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
@RequestMapping("/hr/position")
public class HrPositionController extends BaseController {

    @Autowired
    HrPositionService hrOrgPositionService;

    @RequestMapping("/query")
    public ResponseData query(@RequestBody HrPosition condition, int pageNum, int pageSize) {
        List<HrPosition> list = hrOrgPositionService.query(condition, pageNum, pageSize);
        return new ResponseData(list);
    }

    @RequestMapping("/submit")
    public ResponseData submit(@RequestBody HrPosition hrOrgPosition) {
        if (!getValidator().isValid(hrOrgPosition)) {
            return new ResponseData(false, getValidator().getErrors(hrOrgPosition));
        }
        hrOrgPositionService.submit(hrOrgPosition);
        return new ResponseData(true, "保存成功");
    }

    @RequestMapping("/remove")
    public ResponseData remove(@RequestParam("selectedIds") Long[] selectedIds) {
        hrOrgPositionService.remove(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    @RequestMapping("/selectByPositionId")
    public HrPosition selectByPositionId(@RequestParam Long positionId) {
        return hrOrgPositionService.selectByPositionId(positionId);
    }
}
