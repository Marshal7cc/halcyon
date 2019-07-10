package com.marshal.halcyon.attachment.service;


import com.marshal.halcyon.attachment.exception.AttachmentException;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.service.BaseService;

import com.marshal.halcyon.attachment.entity.SysAttachment;

import javax.servlet.http.HttpServletRequest;

public interface SysAttachmentService extends BaseService<SysAttachment> {

    /**
     * 附件上传通用方法
     *
     * @param request
     * @throws AttachmentException
     */
    void upload(HttpServletRequest request) throws AttachmentException;

}