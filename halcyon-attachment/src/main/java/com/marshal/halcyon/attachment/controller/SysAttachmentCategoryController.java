package com.marshal.halcyon.attachment.controller;


import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.entity.ResponseData;

import com.marshal.halcyon.attachment.entity.SysAttachmentCategory;
import com.marshal.halcyon.attachment.service.SysAttachmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SysAttachmentCategoryController extends BaseController {

    @Autowired
    private SysAttachmentCategoryService service;


    @RequestMapping(value = "/sys/attachment/category/query")
    public ResponseData query(SysAttachmentCategory dto,
                              @RequestParam int pageNum,
                              @RequestParam int pageSize,
                              HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        return new ResponseData(service.select(dto, pageNum, pageSize));
    }

    @RequestMapping(value = "/sys/attachment/category/save")
    public ResponseData update(@RequestBody SysAttachmentCategory dto,
                               HttpServletRequest request) {
        if (getValidator().hasError(dto)) {
            return new ResponseData(false, getValidator().getErrors(dto));
        }
        service.save(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/sys/attachment/category/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        service.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }
}