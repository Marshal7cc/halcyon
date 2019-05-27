package com.marshal.halcyon.ureport.controller;


import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.entity.ResponseData;

import com.marshal.halcyon.ureport.entity.SysReport;
import com.marshal.halcyon.ureport.service.SysReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SysReportController extends BaseController{

    @Autowired
    private SysReportService service;


    @RequestMapping(value = "/sys/report/query")
    public ResponseData query(SysReport dto,
        @RequestParam int pageNum,
        @RequestParam int pageSize,
        HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        return new ResponseData(service.select(dto,pageNum,pageSize));
    }

    @RequestMapping(value = "/sys/report/save")
    public ResponseData update(@RequestBody SysReport dto,
    HttpServletRequest request){
        if (getValidator().hasError(dto)) {
            return new ResponseData(false, getValidator().getErrors(dto));
        }
        service.save(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/sys/report/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        service.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }
}