package com.car4s.search.dao;

import com.car4s.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by 张少强
 */
public interface SearchDao {
    SearchResult search(SolrQuery solrQuery) throws Exception;
}
