package com.marshal.halcyon.account.service;


import com.marshal.halcyon.account.entity.SysUser;
import com.marshal.halcyon.core.service.BaseService;

import java.util.List;
import java.util.Map;

public interface SysUserService extends BaseService<SysUser> {

    List<Map> getUserOptions();

}
