package com.marshal.halcyon.base.account.service;


import com.marshal.halcyon.base.account.entity.SysUser;
import com.marshal.halcyon.core.service.BaseService;

import java.util.List;
import java.util.Map;

public interface SysUserService extends BaseService<SysUser> {

    List<SysUser> selectUsers(int pageNum, int pageSize, SysUser condition);

    List<Map> getUserOptions();

}
