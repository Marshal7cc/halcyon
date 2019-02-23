package com.marshal.halcyon.solr.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SolrService {

    Map<String, Object> query(String keywords, int start, int size) throws IOException, SolrServerException;

    Map<String,Object>  query(String keywords,int start,int size,boolean hlFlag,String hlField) throws IOException, SolrServerException;

    Object queryById(String id) throws IOException, SolrServerException;

    void save(Object object) throws IOException, SolrServerException;

    void deleteById(String id) throws IOException, SolrServerException;

    void deleteById(List<String> ids) throws IOException, SolrServerException;

    void deleteAll() throws IOException, SolrServerException;


}
