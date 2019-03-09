package com.marshal.halcyon.oauth2.controller;

import com.marshal.halcyon.core.component.ResponseData;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.util.ResponseUtils;
import com.marshal.halcyon.oauth2.entity.OauthClientDetails;
import com.marshal.halcyon.oauth2.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/3/8
 * @desc:
 */
@RestController
@RequestMapping("/oauth2/clients")
public class OauthClientDetailsController extends BaseController {

    @Autowired
    OauthClientDetailsService oauthClientDetailsService;

    @RequestMapping("/query")
    public ResponseData query(@RequestBody OauthClientDetails condition,
                              @RequestParam int pageNum,
                              @RequestParam int pageSize) {
        List<OauthClientDetails> list = oauthClientDetailsService.select(condition, pageNum, pageSize);
        return new ResponseData(list);
    }

    @RequestMapping("/submit")
    public ResponseData save(@RequestBody OauthClientDetails oauthClientDetails) {
        if (!getValidator().isValid(oauthClientDetails)) {
            return new ResponseData(false, getValidator().getErrors(oauthClientDetails));
        }
        oauthClientDetailsService.updateByPrimaryKeySelective(oauthClientDetails);
        return ResponseUtils.responseOk("保存成功");
    }

    @RequestMapping("/remove")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        oauthClientDetailsService.batchDelete(selectedIds);
        return ResponseUtils.responseOk("删除成功");
    }

    @RequestMapping("/queryById")
    public OauthClientDetails queryById(@RequestParam Long id) {
        return oauthClientDetailsService.selectByPrimaryKey(id);
    }

}
