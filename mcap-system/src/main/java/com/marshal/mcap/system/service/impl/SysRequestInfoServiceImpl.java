package com.marshal.mcap.system.service.impl;

import com.marshal.mcap.core.beans.SysRequestInfo;
import com.marshal.mcap.system.mapper.SysRequestInfoMapper;
import com.marshal.mcap.system.service.SysRequestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auth: Marshal
 * @date: 2018/12/7
 * @desc:
 */
@Service
public class SysRequestInfoServiceImpl implements SysRequestInfoService {

    @Autowired
    SysRequestInfoMapper sysRequestInfoMapper;

    @Override
    public void insertRecord(SysRequestInfo sysRequestInfo) {
        sysRequestInfoMapper.insertSelective(sysRequestInfo);
    }
}
