package com.marshal.halcyon.es.component;

import java.util.List;
import java.util.Map;

public interface BaseElasticsearchService<T> {

    void createIndex(Class<T> clazz);

    void deleteIndex(Class<T> clazz);

    void putMapping(Class<T> clazz);

    List<T> query(String keyword, Class<T> clazz);

    Map<String, Object> queryHighlight(String keyword, String indexName, String... fieldNames);

    Map<String, Object> queryHighlight(int pageNo, int pageSize, String keyword, String indexName, String... fieldNames);

}
