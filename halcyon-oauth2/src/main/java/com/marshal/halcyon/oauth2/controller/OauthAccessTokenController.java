package com.marshal.halcyon.oauth2.controller;

import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.util.ResponseUtil;
import com.marshal.halcyon.oauth2.entity.OauthAccessToken;
import com.marshal.halcyon.oauth2.service.OauthAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth: Marshal
 * @date: 2019/3/6
 * @desc:
 */
@RestController
@RequestMapping("/oauth2/accessToken")
public class OauthAccessTokenController extends BaseController {

    @Autowired
    OauthAccessTokenService oauthAccessTokenService;

    @RequestMapping("/query")
    public ResponseData query(@RequestBody OauthAccessToken condition,
                              @RequestParam int pageNum,
                              @RequestParam int pageSize) {
        return new ResponseData(oauthAccessTokenService.select(condition, pageNum, pageSize));
    }

    @RequestMapping("/submit")
    public ResponseData save(@RequestBody OauthAccessToken oauthClientDetails) {
        if (!getValidator().hasError(oauthClientDetails)) {
            return new ResponseData(false, getValidator().getErrors(oauthClientDetails));
        }
        oauthAccessTokenService.save(oauthClientDetails);
        return ResponseUtil.responseOk("保存成功");
    }

    @RequestMapping("/remove")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        oauthAccessTokenService.batchDelete(selectedIds);
        return ResponseUtil.responseOk("删除成功");
    }

    @RequestMapping("/queryById")
    public OauthAccessToken queryById(@RequestParam Long id) {
        return oauthAccessTokenService.selectByPrimaryKey(id);
    }
}
