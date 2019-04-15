package com.marshal.halcyon.base.account.controller;

import com.marshal.halcyon.base.account.entity.SysRole;
import com.marshal.halcyon.base.account.entity.SysUser;
import com.marshal.halcyon.base.account.service.SysRoleFunctionService;
import com.marshal.halcyon.base.account.service.SysRoleService;
import com.marshal.halcyon.base.account.service.SysUserService;
import com.marshal.halcyon.base.function.service.SysFunctionService;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysFunctionService sysFunctionService;

    @Autowired
    SysRoleFunctionService sysRoleFunctionService;

    @RequestMapping("/query")
    public ResponseData query(@RequestBody SysRole condition, int pageNum, int pageSize) {
        List<SysRole> list = sysRoleService.select(condition, pageNum, pageSize);
        return new ResponseData(list);
    }

    @RequestMapping("/submit")
    public ResponseData save(@RequestBody SysRole sysRole) {
        if (getValidator().hasError(sysRole)) {
            return new ResponseData(false, getValidator().getErrors(sysRole));
        }
        if (sysRoleService.save(sysRole) == 0) {
            return ResponseUtil.responseErr();
        }
        return ResponseUtil.responseOk();
    }

    @RequestMapping("/remove")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        sysRoleService.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    @RequestMapping("/functionAssignList")
    public List<Map> selectRoleFunctionAssign(HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        return sysFunctionService.selectRoleFunctionAssignList(10001L);
    }
}
