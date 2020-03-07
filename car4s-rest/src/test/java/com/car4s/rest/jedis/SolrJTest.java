package com.car4s.rest.jedis;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Created by 张少强
 */
public class SolrJTest {
    @Test
    public void addDocument() throws Exception{
        //创建链接
        SolrServer solrServer = new HttpSolrServer("http://192.168.117.131:8080/solr");
         //创建文档对象
        SolrInputDocument solrInputFields = new SolrInputDocument();
        solrInputFields.addField("id","test001");
        solrInputFields.addField("item_title", "测试商品");
        solrInputFields.addField("item_price", 54321);
        solrServer.add(solrInputFields);
        solrServer.commit();
    }

    @Test
    public void deleteDocument() throws Exception{
        SolrServer solrServer=new HttpSolrServer("http://192.168.117.131:8080/solr");
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }
}
