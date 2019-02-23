package com.marshal.halcyon.solr.service.impl;

import com.marshal.halcyon.solr.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc:
 */
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    SolrClient solrClient;

    @Value("${spring.data.solr.default-field}")
    private String defaultField;

    @Override
    public Map<String, Object> query(String keywords, int start, int size) throws IOException, SolrServerException {
        return this.query(keywords, start, size, false, null);
    }

    @Override
    public Map<String, Object> query(String keywords, int start, int size, boolean hlFlag, String hlField) throws IOException, SolrServerException {
        // 查询条件
        SolrQuery params = new SolrQuery();
        params.set("q", keywords);
        params.addSort("id", SolrQuery.ORDER.asc);
        params.setStart(start);
        params.setRows(size);
        // 默认域 查询的域
        params.set("df", defaultField);
        // 只查询指定域-->返回的属性,默认全部
//        params.set("fl", "id,hal_user_name,hal_email");
        // 开启高亮
        params.setHighlight(hlFlag);
        if (hlFlag) {
            params.set("hl.fl", hlField);
            params.setHighlightSimplePre("<span style='color:red'>");
            params.setHighlightSimplePost("</span>");
        }
        QueryResponse queryResponse = solrClient.query(params);

        SolrDocumentList rows = queryResponse.getResults();
        long total = rows.getNumFound();
        Map<String, Map<String, List<String>>> hlRows = queryResponse.getHighlighting();

        Map<String, Object> result = new HashMap();
        result.put("total", total);
        result.put("rows", rows);
        result.put("hlRows", hlRows);
        return result;

    }

    @Override
    public Object queryById(String id) throws IOException, SolrServerException {
        return solrClient.getById(id);
    }

    @Override
    public void save(Object object) throws IOException, SolrServerException {
        if (object instanceof Collection) {
            solrClient.addBeans(((Collection) object).iterator());
        } else {
            solrClient.addBean(object);
        }
        solrClient.commit();
    }

    @Override
    public void deleteById(String id) throws IOException, SolrServerException {
        solrClient.deleteById(id);
        solrClient.commit();
    }

    @Override
    public void deleteById(List<String> ids) throws IOException, SolrServerException {
        solrClient.deleteById(ids);
        solrClient.commit();
    }

    @Override
    public void deleteAll() throws IOException, SolrServerException {
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
    }
}
