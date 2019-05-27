package com.marshal.halcyon.ureport.service.impl;


import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.marshal.halcyon.ureport.entity.SysReport;
import com.marshal.halcyon.ureport.service.SysReportService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysReportServiceImpl extends BaseServiceImpl<SysReport> implements SysReportService{

}