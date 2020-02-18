package com.car4s.service.Impl;

import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.mapper.TbItemParamItemMapper;
import com.car4s.generator.pojo.TbItemParam;
import com.car4s.generator.pojo.TbItemParamItem;
import com.car4s.generator.pojo.TbItemParamItemExample;
import com.car4s.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 张少强
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public String getItemParamByItemId(Long itemId) {
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
        if(list == null||list.size()==0){
            return "";
        }
        tbItemParamItem = list.get(0);
        String paramData = tbItemParamItem.getParamData();
        List<Map> paramList  = JsonUtil.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map map : paramList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> params = (List<Map>) map.get("params");
            for (Map map2 : params) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                sb.append("            <td>"+map2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
