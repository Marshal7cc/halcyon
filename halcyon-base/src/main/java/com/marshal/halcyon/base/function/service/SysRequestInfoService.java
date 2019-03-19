package com.marshal.halcyon.base.function.service;

import com.marshal.halcyon.base.function.entity.SysRequestInfo;
import com.marshal.halcyon.core.service.BaseService;

/**
 * @auth: Marshal
 * @date: 2018/12/7
 * @desc: 系统请求
 */
public interface SysRequestInfoService extends BaseService<SysRequestInfo> {

    void recordSysRequest();

}
