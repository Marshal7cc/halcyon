package com.marshal.halcyon.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.account.entity.SysUser;
import com.marshal.halcyon.account.mapper.SysUserMapper;
import com.marshal.halcyon.account.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<SysUser> select(SysUser condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysUserMapper.query(condition);
    }

    @Override
    public SysUser selectByPrimaryKey(Long id) {
        SysUser sysUser = (SysUser) redisTemplate.opsForHash().get("halcyon:user", id.toString());
        if (sysUser != null) {
            return (SysUser) sysUser;
        } else {
            redisTemplate.opsForHash().put("halcyon:user", id.toString(), sysUserMapper.selectByPrimaryKey(id));
            return sysUserMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public void save(SysUser SysUser) {
        if (SysUser.getUserId() != null) {
            sysUserMapper.updateByPrimaryKey(SysUser);
        } else {
            sysUserMapper.insert(SysUser);
        }
    }

    @Override
    public void delete(Long[] idList) {
        for (Long id : idList) {
            sysUserMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> getUserOptions() {
        return sysUserMapper.getUserOptions();
    }
}
