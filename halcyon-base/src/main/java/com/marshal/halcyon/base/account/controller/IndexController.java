package com.marshal.halcyon.base.account.controller;

import com.marshal.halcyon.base.account.entity.SysRole;
import com.marshal.halcyon.base.account.entity.SysUser;
import com.marshal.halcyon.base.account.service.SysRoleService;
import com.marshal.halcyon.base.account.service.SysUserService;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.util.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @auth: Marshal
 * @date: 2019/4/7
 * @desc: 主页controller
 */
@RestController
public class IndexController extends BaseController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView mv,
                              HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        Long userId = sessionContext.getUserId();
        Long roleId = sessionContext.getRoleId();

        SysUser user = userService.selectByPrimaryKey(userId);

        SysRole role = roleService.selectByPrimaryKey(roleId);

        mv.addObject("userName", user.getUserName());
        mv.addObject("avatar", user.getAvatar());
        mv.addObject("roleName", role.getRoleName());
        mv.setViewName("index");
        return mv;
    }

}
