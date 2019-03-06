package com.marshal.halcyon.system.mapper;

import com.marshal.halcyon.system.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser>, Serializable {

    List<SysUser> query(SysUser condition);

    List<Map> getUserOptions();

    SysUser getUserByUsername(String username);
}