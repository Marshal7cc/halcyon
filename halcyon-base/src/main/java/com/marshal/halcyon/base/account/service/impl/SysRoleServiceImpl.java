package com.marshal.halcyon.base.account.service.impl;

import com.marshal.halcyon.base.account.entity.SysRole;
import com.marshal.halcyon.base.account.service.SysRoleService;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auth: Marshal
 * @date: 2019/3/31
 * @desc:
 */
@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
}
