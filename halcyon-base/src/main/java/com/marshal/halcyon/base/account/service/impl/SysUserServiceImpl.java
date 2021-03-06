package com.marshal.halcyon.base.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.base.account.entity.SysUser;
import com.marshal.halcyon.base.account.mapper.SysUserMapper;
import com.marshal.halcyon.base.account.service.SysUserService;
import com.marshal.halcyon.base.ueditor.util.OSSClientUtil;
import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/10/26
 * Time: 20:10
 * Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectUsers(int pageNum, int pageSize, SysUser condition) {
        PageHelper.startPage(pageNum, pageSize);
        return sysUserMapper.selectUsers(condition);
    }

    @Override
    public List<Map> getUserOptions() {
        return sysUserMapper.getUserOptions();
    }

    @Override
    public String uploadAvatar(SessionContext sessionContext, MultipartFile file) throws Exception {
        String avatarFileName = OSSClientUtil.uploadImgToOSS(file, "avatar");
        String avatarPath = OSSClientUtil.getAvatorImgUrl(avatarFileName);

        Long userId = sessionContext.getUserId();

        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        user.setAvatar(avatarPath);
        sysUserMapper.updateByPrimaryKeySelective(user);

        return avatarPath;
    }
}
