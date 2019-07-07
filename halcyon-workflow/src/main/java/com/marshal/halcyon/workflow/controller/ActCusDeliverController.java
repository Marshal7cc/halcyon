package com.marshal.halcyon.workflow.controller;


import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.entity.ResponseData;

import com.marshal.halcyon.workflow.entity.ActCusDeliver;
import com.marshal.halcyon.workflow.service.ActCusDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ActCusDeliverController extends BaseController {

    @Autowired
    private ActCusDeliverService service;


    @RequestMapping(value = "/act/cus/deliver/query")
    public ResponseData query(ActCusDeliver dto,
                              @RequestParam int pageNum,
                              @RequestParam int pageSize,
                              HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        return new ResponseData(service.select(dto, pageNum, pageSize));
    }

    @RequestMapping(value = "/act/cus/deliver/save")
    public ResponseData update(@RequestBody ActCusDeliver dto,
                               HttpServletRequest request) {
        if (getValidator().hasError(dto)) {
            return new ResponseData(false, getValidator().getErrors(dto));
        }
        service.save(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/act/cus/deliver/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        service.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }
}