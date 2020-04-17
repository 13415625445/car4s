package com.car4s.portal.pojo;

import java.util.List;
import java.util.Map;

/**
 * Created by 张少强
 */
public class ContrastItem {
    private ItemInfo item;

    public List<Map> getJsonList() {
        return jsonList;
    }

    public void setJsonList(List<Map> jsonList) {
        this.jsonList = jsonList;
    }

    private List<Map> jsonList;

    public ItemInfo getItem() {
        return item;
    }

    public void setItem(ItemInfo item) {
        this.item = item;
    }


}
