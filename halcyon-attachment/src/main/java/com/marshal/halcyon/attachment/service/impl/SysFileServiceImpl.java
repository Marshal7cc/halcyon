package com.marshal.halcyon.attachment.service.impl;


import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.halcyon.attachment.entity.SysFile;
import com.marshal.halcyon.attachment.service.SysFileService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysFileServiceImpl extends BaseServiceImpl<SysFile> implements SysFileService{

}