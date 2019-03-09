package com.marshal.halcyon.solr.controller;

import com.marshal.halcyon.solr.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc:
 */
@RestController
@RequestMapping("/solr")
public class SolrController {

    private static final String hlField = "hal_user_name,hal_email";

    @Autowired
    SolrService solrService;

    @RequestMapping("/search/all")
    public Map searchAll(@RequestParam int pageNum,
                         @RequestParam int pageSize,
                         String q) {
        try {
            Map result = solrService.query(q, pageNum, pageSize, true, hlField);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap();
    }
}
