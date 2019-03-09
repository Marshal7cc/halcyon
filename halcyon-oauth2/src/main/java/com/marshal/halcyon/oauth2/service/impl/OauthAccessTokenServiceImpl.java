package com.marshal.halcyon.oauth2.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import com.marshal.halcyon.oauth2.entity.OauthAccessToken;
import com.marshal.halcyon.oauth2.mapper.OauthAccessTokenMapper;
import com.marshal.halcyon.oauth2.service.OauthAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/3/6
 * @desc:
 */
@Service
public class OauthAccessTokenServiceImpl extends BaseServiceImpl<OauthAccessToken> implements OauthAccessTokenService {

    @Autowired
    OauthAccessTokenMapper oauthAccessTokenMapper;

    @Override
    public OauthAccessToken addTokenLogs(OauthAccessToken token) {
        oauthAccessTokenMapper.insertSelective(token);
        return token;
    }

    @Override
    public List<OauthAccessToken> selectOauthAccessTokens(OauthAccessToken condition, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        return oauthAccessTokenMapper.select(condition);
    }
}
