package com.marshal.halcyon.oauth2.service;

import com.marshal.halcyon.oauth2.entity.OauthAccessToken;

import java.util.List;

public interface OauthAccessTokenService {

    OauthAccessToken addTokenLogs(OauthAccessToken token);

    List<OauthAccessToken> selectOauthAccessTokens(OauthAccessToken condition, int pageNum, int pageSize);
}
