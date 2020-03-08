package com.car4s.portal.pojo;

import com.car4s.generator.pojo.TbItem;

public class ItemInfo extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
