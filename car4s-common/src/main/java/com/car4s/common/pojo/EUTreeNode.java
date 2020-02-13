package com.car4s.common.pojo;

/**
 * Created by 张少强
 */
public class EUTreeNode {

    /**
     * 唯一标识id
     */
    private long id;

    /**
     * 商品分类名称
     */
    private String text;

    /**
     * 是否叶子节点
     */
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
