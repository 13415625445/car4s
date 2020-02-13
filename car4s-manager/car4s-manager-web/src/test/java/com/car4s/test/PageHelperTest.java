package com.car4s.test;

import com.car4s.generator.mapper.TbItemMapper;
import com.car4s.generator.pojo.TbItem;
import com.car4s.generator.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 张少强
 */
public class PageHelperTest {
    @Test
    public void testHelperTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(1,10);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        for (TbItem tbItem : tbItems) {
            System.out.println(tbItem);
        }
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        Long total = pageInfo.getTotal();
        System.out.println("一共有数据"+total+"条");
    }
}
