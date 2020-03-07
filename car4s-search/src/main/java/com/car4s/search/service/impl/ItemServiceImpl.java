package com.car4s.search.service.impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.ExceptionUtil;
import com.car4s.search.mapper.ItemMapper;
import com.car4s.search.pojo.Item;
import com.car4s.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;
    @Override
    public Car4sResult importAllItems(){
        try {
        List<Item> list = itemMapper.getItemList();
        for (Item item : list) {
            SolrInputDocument solrInputFields = new SolrInputDocument();
            solrInputFields.setField("id", item.getId());
            solrInputFields.setField("item_title", item.getTitle());
            solrInputFields.setField("item_sell_point", item.getSell_point());
            solrInputFields.setField("item_price", item.getPrice());
            solrInputFields.setField("item_image", item.getImage());
            solrInputFields.setField("item_category_name", item.getCategory_name());
            solrInputFields.setField("item_desc", item.getItem_des());
            solrServer.add(solrInputFields);
        }
        solrServer.commit();
            } catch (Exception e) {
                e.printStackTrace();
                return Car4sResult.build(500,ExceptionUtil.getStackTrace(e));
            }
        return Car4sResult.ok();
    }
}
