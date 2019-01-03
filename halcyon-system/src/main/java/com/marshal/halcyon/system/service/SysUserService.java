package com.marshal.halcyon.system.service;


import com.marshal.halcyon.system.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService{

    List<SysUser> select(SysUser condition, int pageNum, int pageSize);

    public SysUser selectByPrimaryKey(Long id);

    public void save(SysUser SysUser);

    public void delete(Long[] idList);

    List<Map> getUserOptions();
}
