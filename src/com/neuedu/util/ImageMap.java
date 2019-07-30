package com.neuedu.util;

import java.awt.Image;
import java.util.HashMap;

public class ImageMap {

    public static final HashMap<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\img\\bg\\bg01.png"));
        map.put("hero01",ImageUtil.getImage("com\\neuedu\\img\\hero\\hero01.png"));
    }

    public static Image get(String key){
        return map.get(key);
    }

}
