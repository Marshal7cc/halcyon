package com.marshal.halcyon.es.component;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch基础service，屏蔽掉部分elasticsearchTemplate的方法
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseElasticsearchServiceImpl<T> implements BaseElasticsearchService<T> {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void createIndex(Class<T> clazz) {
        elasticsearchTemplate.createIndex(clazz);
    }

    @Override
    public void deleteIndex(Class<T> clazz) {
        elasticsearchTemplate.deleteIndex(clazz);
    }

    @Override
    public void putMapping(Class<T> clazz) {
        elasticsearchTemplate.putMapping(clazz);
    }

    @Override
    public List<T> query(String keyword, Class<T> clazz) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(new QueryStringQueryBuilder(keyword))
                .withSort(SortBuilders.scoreSort().order(SortOrder.ASC))
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }

    @Override
    public Map<String, Object> queryHighlight(String keyword, String indexName, String... fieldNames) {
        // 构造查询条件,使用标准分词器.
        QueryBuilder matchQuery = createQueryBuilder(keyword, fieldNames);

        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = createHighlightBuilder(fieldNames);

        // 设置查询字段
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(indexName)
                .setQuery(matchQuery)
                .highlighter(highlightBuilder)
                .get();

        // 返回搜索结果
        SearchHits hits = response.getHits();

        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", hits.getTotalHits());
        result.put("rows", getHitList(hits));
        return result;
    }

    @Override
    public Map<String, Object> queryHighlight(int pageNo, int pageSize, String keyword, String indexName, String... fieldNames) {
        // 构造查询条件,使用标准分词器.
        QueryBuilder matchQuery = createQueryBuilder(keyword, fieldNames);

        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = createHighlightBuilder(fieldNames);

        // 设置查询字段
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(indexName)
                .setQuery(matchQuery)
                .highlighter(highlightBuilder)
                .setFrom((pageNo - 1) * pageSize)
                .setSize(pageNo * pageSize)
                .get();


        // 返回搜索结果
        SearchHits hits = response.getHits();

        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", hits.getTotalHits());
        result.put("rows", getHitList(hits));
        return result;
    }

    /**
     * 构造查询条件
     *
     * @param keyword
     * @param fieldNames
     * @return
     */
    private QueryBuilder createQueryBuilder(String keyword, String... fieldNames) {
        // 构造查询条件,使用标准分词器
        return QueryBuilders.multiMatchQuery(keyword, fieldNames)   // matchQuery(),单字段搜索
                .analyzer("ik_smart")
                .operator(Operator.OR);
    }


    private HighlightBuilder createHighlightBuilder(String... fieldNames) {
        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .preTags("<span style='color:red'>")
                .postTags("</span>");
        // 设置高亮字段
        for (String fieldName : fieldNames)
            highlightBuilder.field(fieldName);

        return highlightBuilder;
    }

    private List<Map<String, Object>> getHitList(SearchHits hits) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;
        for (SearchHit searchHit : hits) {
            map = new HashMap<>();
            // 处理源数据
            map.put("source", searchHit.getSourceAsMap());
            // 处理高亮数据
            Map<String, Object> hitMap = new HashMap<>();
            searchHit.getHighlightFields().forEach((k, v) -> {
                String hight = "";
                for (Text text : v.getFragments()) hight += text.string();
                hitMap.put(v.getName(), hight);
            });
            map.put("highlight", hitMap);
            list.add(map);
        }
        return list;
    }
}
