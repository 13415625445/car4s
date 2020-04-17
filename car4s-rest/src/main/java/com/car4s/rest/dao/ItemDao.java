package com.car4s.rest.dao;

import com.car4s.generator.pojo.TbItem;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * Created by 张少强
 */
@Repository
public interface ItemDao {
    @Select("select * from tb_item")
    public List<TbItem> selectAll();
}
