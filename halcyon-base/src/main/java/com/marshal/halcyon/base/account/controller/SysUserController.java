package com.marshal.halcyon.base.account.controller;

import com.marshal.halcyon.base.account.entity.SysUser;
import com.marshal.halcyon.base.account.service.SysUserService;
import com.marshal.halcyon.core.annotation.AccessLimit;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "用户管理接口")
@RestController
@RequestMapping("/account/user")
public class SysUserController extends BaseController {

    private static final String DEFAULT_PASSWORD = "123456";

    @Autowired
    SysUserService sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ApiOperation("用户查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int")
    })
    @RequestMapping("/query")
    @AccessLimit(period = 60, count = 10)
    public ResponseData query(@RequestBody SysUser condition, int pageNum, int pageSize) {
        List<SysUser> list = sysUserService.selectUsers(pageNum, pageSize, condition);
        return new ResponseData(list);
    }

    @RequestMapping("/save")
    public ResponseData save(@RequestBody SysUser sysUser) {
        if (getValidator().hasError(sysUser)) {
            return new ResponseData(false, getValidator().getErrors(sysUser));
        }
        sysUser.setPasswordEncrypted(passwordEncoder.encode(DEFAULT_PASSWORD));
        if (sysUserService.save(sysUser) == 0) {
            return ResponseUtil.responseErr();
        }
        return ResponseUtil.responseOk();
    }

    @RequestMapping("/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        sysUserService.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    @RequestMapping("/queryById")
    public SysUser queryById(@RequestParam Long id) {
        return sysUserService.selectByPrimaryKey(id);
    }

    @RequestMapping("/getOptions")
    public List<Map> getOptions() {
        return sysUserService.getUserOptions();
    }
}
