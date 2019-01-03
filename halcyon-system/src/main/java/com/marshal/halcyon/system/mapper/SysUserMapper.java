package com.marshal.halcyon.system.mapper;

import com.marshal.halcyon.system.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {

    List<Map> getUserOptions();

    SysUser getUserByUsername(String username);
}