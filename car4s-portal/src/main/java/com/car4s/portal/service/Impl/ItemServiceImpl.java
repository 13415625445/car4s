package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.pojo.TbItemDesc;
import com.car4s.generator.pojo.TbItemParamItem;
import com.car4s.portal.pojo.ContrastItem;
import com.car4s.portal.pojo.ItemInfo;
import com.car4s.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 张少强
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Value("${ALL_ITEM_URL}")
    private String ALL_ITEM_URL;

    @Override
    public ItemInfo getItemById(Long itemId) {
        try {
            //调用rest的服务查询商品基本信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if (!StringUtils.isBlank(json)) {
                Car4sResult car4sResult = Car4sResult.formatToPojo(json, ItemInfo.class);
                if (car4sResult.getStatus() == 200) {
                    ItemInfo item = (ItemInfo) car4sResult.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getItemDescById(Long itemId) {

        try {
            //查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            //转换成java对象
            Car4sResult car4sResult = Car4sResult.formatToPojo(json, TbItemDesc.class);
            if (car4sResult.getStatus() == 200) {
                TbItemDesc itemDesc = (TbItemDesc) car4sResult.getData();
                //取商品描述信息
                String result = itemDesc.getItemDesc();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParam(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            //把json转换成java对象
            Car4sResult car4sResult = Car4sResult.formatToPojo(json, TbItemParamItem.class);
            if (car4sResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) car4sResult.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtil.jsonToList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");
                for (Map m1 : jsonList) {
                    sb.append("        <tr>\n");
                    sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
                    sb.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for (Map m2 : list2) {
                        sb.append("        <tr>\n");
                        sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
                        sb.append("            <td>" + m2.get("v") + "</td>\n");
                        sb.append("        </tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public ContrastItem contrastItemById(long itemId) {
        ContrastItem contrastItem = new ContrastItem();
        try {
            //调用rest的服务查询商品基本信息
            String json1 = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if (!StringUtils.isBlank(json1)) {
                Car4sResult car4sResult = Car4sResult.formatToPojo(json1, ItemInfo.class);
                if (car4sResult.getStatus() == 200) {
                    ItemInfo item = (ItemInfo) car4sResult.getData();
                    contrastItem.setItem(item);
                }
            }
            String json2 = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            //把json转换成java对象
            if (!StringUtils.isBlank(json2)) {
                Car4sResult car4sResult = Car4sResult.formatToPojo(json2, TbItemParamItem.class);
                if (car4sResult.getStatus() == 200) {
                    TbItemParamItem itemParamItem = (TbItemParamItem) car4sResult.getData();
                    String paramData = itemParamItem.getParamData();
                    List<Map> jsonList = JsonUtil.jsonToList(paramData, Map.class);
                    contrastItem.setJsonList(jsonList);
                }
            }
            return contrastItem;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemInfo> getItemList() {
        try {
            //调用rest的服务查询车辆列表
            String json = HttpClientUtil.doGet(REST_BASE_URL + ALL_ITEM_URL);
            if (!StringUtils.isBlank(json)) {
                Car4sResult car4sResult = Car4sResult.formatToList(json, ItemInfo.class);
                if (car4sResult.getStatus() == 200) {
                    List<ItemInfo> list = (List<ItemInfo>) car4sResult.getData();
                    return list;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
