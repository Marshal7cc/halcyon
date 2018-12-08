package com.marshal.mcap.system.controller;

import com.marshal.mcap.core.controller.BaseController;
import com.marshal.mcap.core.component.ResponseData;
import com.marshal.mcap.system.entity.SysUser;
import com.marshal.mcap.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    SysUserService sysUserService;

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
