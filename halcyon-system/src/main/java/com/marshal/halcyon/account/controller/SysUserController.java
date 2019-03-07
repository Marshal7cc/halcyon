package com.marshal.halcyon.account.controller;

import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.component.ResponseData;
import com.marshal.halcyon.account.entity.SysUser;
import com.marshal.halcyon.account.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/10/26
 * Time: 20:35
 * Description:
 */
@RestController
@RequestMapping("/account/user")
public class SysUserController extends BaseController {

    private static final String DEFAULT_PASSWORD = "123456";

    @Autowired
    SysUserService sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/query")
    public ResponseData query(@RequestBody SysUser condition, int pageNum, int pageSize) {
        List<SysUser> list = sysUserService.select(condition, pageNum, pageSize);
        return new ResponseData(list);
    }

    @RequestMapping("/save")
    public ResponseData save(@RequestBody SysUser sysUser) {
        if (!getValidator().isValid(sysUser)) {
            return new ResponseData(false, getValidator().getErrors(sysUser));
        }
        sysUser.setPasswordEncrypted(passwordEncoder.encode(DEFAULT_PASSWORD));
        sysUserService.save(sysUser);
        return new ResponseData(true, "保存成功");
    }

    @RequestMapping("/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        sysUserService.delete(selectedIds);
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
