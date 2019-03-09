package com.marshal.halcyon.oauth2.service;

import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.oauth2.entity.OauthAccessToken;

import java.util.List;

public interface OauthAccessTokenService extends BaseService<OauthAccessToken> {

    OauthAccessToken addTokenLogs(OauthAccessToken token);

    List<OauthAccessToken> selectOauthAccessTokens(OauthAccessToken condition, int pageNum, int pageSize);
}
