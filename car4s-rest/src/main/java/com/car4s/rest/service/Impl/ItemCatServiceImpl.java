package com.car4s.rest.service.Impl;

import com.car4s.generator.mapper.TbItemCatMapper;
import com.car4s.generator.pojo.TbItemCat;
import com.car4s.generator.pojo.TbItemCatExample;
import com.car4s.rest.pojo.CatNode;
import com.car4s.rest.pojo.CatResult;
import com.car4s.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张少强
 */

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return catResult;
    }

    private List<?> getCatList(long parentId){
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);
        List arrayList = new ArrayList<>();
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            if(tbItemCat.getIsParent()){
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                catNode.setItem(getCatList(tbItemCat.getId()));

                arrayList.add(catNode);
                count++;
                if(parentId ==0&&count>=14){
                    break;
                }
                //如果是叶子节点
            } else {
                arrayList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
            }
        }
        return arrayList;
    }
}
