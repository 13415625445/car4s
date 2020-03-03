package com.car4s.common.pojo;

import java.util.List;

/**
 * Created by 张少强
 */
public class EUDataGridResult {

    /**
     * 总数
     */
    private long total;

    /**
     * 查找到的数据
     */
    private List<?> rows;



    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
