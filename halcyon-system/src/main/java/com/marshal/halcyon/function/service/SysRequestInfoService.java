package com.marshal.halcyon.function.service;

import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.function.entity.SysRequestInfo;

/**
 * @auth: Marshal
 * @date: 2018/12/7
 * @desc: 系统请求
 */
public interface SysRequestInfoService extends BaseService<SysRequestInfo> {

    void recordSysRequest();

}
