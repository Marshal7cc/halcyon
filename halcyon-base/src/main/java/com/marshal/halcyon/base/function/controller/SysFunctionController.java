package com.marshal.halcyon.base.function.controller;


import com.marshal.halcyon.base.function.entity.SysFunction;
import com.marshal.halcyon.base.function.service.SysFunctionService;
import com.marshal.halcyon.core.cache.impl.FunctionCache;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.util.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/5
 * Time: 22:06
 * Description:
 */
@RestController
@RequestMapping("/sys/function")
public class SysFunctionController extends BaseController {

    @Autowired
    SysFunctionService sysFunctionService;

    @Autowired
    private FunctionCache functionCache;

    /**
     * 获取菜单
     *
     * @return
     */
    @RequestMapping("/getMenus")
    public List<SysFunction> getMenuList(HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        List<SysFunction> menuList = sysFunctionService.getMenus(sessionContext.getRoleId());
        return menuList;
    }


    /**
     * 基础crud
     */
    @RequestMapping("/query")
    public ResponseData query(@RequestBody SysFunction condition, int pageNum, int pageSize) {
        List<SysFunction> list = sysFunctionService.selectFunctions(condition, pageNum, pageSize);
        return new ResponseData(list);
    }

    @RequestMapping("/save")
    public ResponseData save(@RequestBody SysFunction sysFunction) {
        if (getValidator().hasError(sysFunction)) {
            return new ResponseData(false, getValidator().getErrors(sysFunction));
        }
        sysFunctionService.save(sysFunction);
        functionCache.update(sysFunction);
        return new ResponseData(true, "保存成功");
    }

    @RequestMapping("/delete")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        sysFunctionService.batchDelete(selectedIds);
        functionCache.delete(selectedIds);
        return new ResponseData(true, "删除成功");
    }

    @RequestMapping("/queryById")
    public SysFunction queryById(@RequestParam Long id) {
        return sysFunctionService.selectByPrimaryKey(id);
    }

    @RequestMapping("/getFunctionOptions")
    public List<Map> getFunctionOptions() {
        return sysFunctionService.getFunctionOptions();
    }
}
