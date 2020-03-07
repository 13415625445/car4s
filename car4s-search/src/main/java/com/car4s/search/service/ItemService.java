package com.car4s.search.service;

import com.car4s.common.pojo.Car4sResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Created by 张少强
 */
public interface ItemService {
    Car4sResult importAllItems() throws IOException, SolrServerException;
}
