package com.marshal.halcyon.attachment.controller;


import com.marshal.halcyon.attachment.exception.AttachmentException;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.entity.ResponseData;

import com.marshal.halcyon.attachment.entity.SysAttachment;
import com.marshal.halcyon.attachment.service.SysAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SysAttachmentController extends BaseController {

    @Autowired
    private SysAttachmentService service;


    @RequestMapping(value = "/sys/attachment/query")
    public ResponseData query(SysAttachment dto,
                              @RequestParam int pageNum,
                              @RequestParam int pageSize,
                              HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        return new ResponseData(service.select(dto, pageNum, pageSize));
    }

    @RequestMapping(value = "/sys/attachment/save")
    public ResponseData update(@RequestBody SysAttachment dto,
                               HttpServletRequest request) {
        if (getValidator().hasError(dto)) {
            return new ResponseData(false, getValidator().getErrors(dto));
        }
        service.save(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/sys/attachment/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        service.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    /**
     * 附件上传
     *
     * @param request
     * @return
     */
    @PostMapping("/sys/attachment/upload")
    public ResponseData upload(HttpServletRequest request) throws AttachmentException {
        service.upload(request);
        return new ResponseData();
    }
}