package com.marshal.halcyon.security.service;

import com.marshal.halcyon.system.entity.SysUser;
import com.marshal.halcyon.system.mapper.SysUserMapper;
import com.marshal.halcyon.security.domain.HalcyonUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @auth: Marshal
 * @date: 2018/11/12
 * @desc: Spring Security UserService
 */
public class HalcyonUserDetailService implements UserDetailsService, AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.getUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("user not found:" + username);
        }

        //授权列表
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //todo:角色授权
//        for(String role:user.getRoleCode()){
//            authorities.add(new SimpleGrantedAuthority(role));
//        }

        UserDetails userDetails = new HalcyonUserDetails(sysUser.getUserId(), sysUser.getUserName(),
                sysUser.getPasswordEncrypted(), true, true, true, true, authorities);
        return userDetails;
    }

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken casAssertionAuthenticationToken) throws UsernameNotFoundException {
        String name = casAssertionAuthenticationToken.getName();
        SysUser sysUser = sysUserMapper.getUserByUsername(name);
        if (sysUser == null) {
            throw new UsernameNotFoundException(name + "不存在");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //todo:角色授权
//        for(String role:user.getRoleCode()){
//            authorities.add(new SimpleGrantedAuthority(role));
//        }

        UserDetails userDetails = new HalcyonUserDetails(sysUser.getUserId(), sysUser.getUserName(),
                sysUser.getPasswordEncrypted(), true, true, true, true, authorities);
        return userDetails;
    }
}
