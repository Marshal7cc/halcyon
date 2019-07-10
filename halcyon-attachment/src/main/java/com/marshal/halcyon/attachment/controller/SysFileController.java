package com.marshal.halcyon.attachment.controller;


import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.entity.ResponseData;

import com.marshal.halcyon.attachment.entity.SysFile;
import com.marshal.halcyon.attachment.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SysFileController extends BaseController {

    @Autowired
    private SysFileService service;


    @RequestMapping(value = "/sys/file/query")
    public ResponseData query(SysFile dto,
                              @RequestParam int pageNum,
                              @RequestParam int pageSize,
                              HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        return new ResponseData(service.select(dto, pageNum, pageSize));
    }

    @RequestMapping(value = "/sys/file/save")
    public ResponseData update(@RequestBody SysFile dto,
                               HttpServletRequest request) {
        if (getValidator().hasError(dto)) {
            return new ResponseData(false, getValidator().getErrors(dto));
        }
        service.save(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/sys/file/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        service.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }
}