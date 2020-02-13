package com.car4s.service.Impl;

import com.car4s.common.pojo.EUTreeNode;
import com.car4s.generator.mapper.TbItemCatMapper;
import com.car4s.generator.pojo.TbItemCat;
import com.car4s.generator.pojo.TbItemCatExample;
import com.car4s.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<EUTreeNode> getItemCatList(long parentId) {

        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);
        ArrayList<EUTreeNode> arrayList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbItemCat.getId());
            euTreeNode.setText(tbItemCat.getName());
            euTreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            arrayList.add(euTreeNode);
        }
        return arrayList;
    }
}
