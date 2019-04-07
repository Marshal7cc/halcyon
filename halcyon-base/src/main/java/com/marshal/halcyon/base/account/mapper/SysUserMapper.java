package com.marshal.halcyon.base.account.mapper;

import com.marshal.halcyon.base.account.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser>, Serializable {

    List<SysUser> selectUsers(SysUser condition);

    List<Map> getUserOptions();

    SysUser getUserByUsername(String username);
}