package com.marshal.mcap.system.mapper;

import com.marshal.mcap.system.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {

    List<Map> getUserOptions();

    SysUser getUserByUsername(String username);
}