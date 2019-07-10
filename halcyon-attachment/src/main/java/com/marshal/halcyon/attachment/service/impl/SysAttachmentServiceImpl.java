package com.marshal.halcyon.attachment.service.impl;


import com.marshal.halcyon.attachment.component.Uploader;
import com.marshal.halcyon.attachment.component.UploaderFactory;
import com.marshal.halcyon.attachment.entity.SysAttachmentCategory;
import com.marshal.halcyon.attachment.exception.AttachmentException;
import com.marshal.halcyon.attachment.mapper.SysAttachmentCategoryMapper;
import com.marshal.halcyon.attachment.mapper.SysAttachmentMapper;
import com.marshal.halcyon.attachment.mapper.SysFileMapper;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.halcyon.attachment.entity.SysAttachment;
import com.marshal.halcyon.attachment.service.SysAttachmentService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysAttachmentServiceImpl extends BaseServiceImpl<SysAttachment> implements SysAttachmentService {

    @Autowired
    private UploaderFactory uploaderFactory;

    @Autowired
    SysAttachmentMapper sysAttachmentMapper;

    @Autowired
    SysAttachmentCategoryMapper sysAttachmentCategoryMapper;

    @Autowired
    SysFileMapper sysFileMapper;

    @Override
    public void upload(HttpServletRequest request) throws AttachmentException {
        Uploader uploader = uploaderFactory.getUploader();
        uploader.init(request);

        uploader.upload();
    }
}