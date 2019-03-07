package com.marshal.halcyon.oauth2.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.oauth2.entity.OauthAccessToken;
import com.marshal.halcyon.oauth2.service.OauthAccessTokenService;
import com.marshal.halcyon.security.domain.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @auth: Marshal
 * @date: 2019/3/6
 * @desc: 自定义TokenStore, 获取token后将token同时存入数据库和redis
 */
@Component
@Slf4j
public class CustomRedisTokenStore extends RedisTokenStore {

    public final static String REDIS_CATALOG = "halcyon:cache:oauth2_token:";

    @Autowired
    OauthAccessTokenService oauthAccessTokenService;

    @Autowired
    StringRedisTemplate redisTemplate;

    public CustomRedisTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        String tokenState = redisTemplate.opsForValue().get(REDIS_CATALOG + tokenValue);
        if (StringUtils.isNotEmpty(tokenState)) {
            OAuth2AccessToken accessToken = super.readAccessToken(tokenValue);
            return accessToken;
        }
        return null;
    }

    @Override
    public void removeAccessToken(String tokenValue) {
        redisTemplate.delete(REDIS_CATALOG + tokenValue);
//        oauthAccessTokenService.revokeToken(token.getValue());
        super.removeAccessToken(tokenValue);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String clientId = authentication.getOAuth2Request().getClientId();
        OauthAccessToken oauthAccessToken = new OauthAccessToken();
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails details = (CustomUserDetails) principal;
            oauthAccessToken.setUserId(details.getUserId());
        }
        oauthAccessToken.setToken(token.getValue());
        oauthAccessToken.setClientId(authentication.getOAuth2Request().getClientId());
        oauthAccessToken.setTokenAccessType(authentication.getOAuth2Request().getGrantType());
        //token获取时间和失效时间
        Calendar expiration = Calendar.getInstance();
        expiration.setTime(token.getExpiration());
        expiration.add(Calendar.SECOND, -token.getExpiresIn());
        oauthAccessToken.setTokenAccessTime(expiration.getTime());
        oauthAccessToken.setTokenExpiresTime(token.getExpiration());
        oauthAccessTokenService.addTokenLogs(oauthAccessToken);
        String tokenString = "";
        try {
            tokenString = new ObjectMapper().writeValueAsString(oauthAccessToken);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        redisTemplate.opsForValue().set(REDIS_CATALOG + token.getValue(), tokenString, token.getExpiresIn(),
                TimeUnit.SECONDS);

    }

}
