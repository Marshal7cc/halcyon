package com.marshal.halcyon.attachment.service.impl;


import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.halcyon.attachment.entity.SysAttachmentCategory;
import com.marshal.halcyon.attachment.service.SysAttachmentCategoryService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysAttachmentCategoryServiceImpl extends BaseServiceImpl<SysAttachmentCategory> implements SysAttachmentCategoryService {

}