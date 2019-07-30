package com.neuedu.util;

import java.awt.Image;
import java.util.HashMap;

public class ImageMap {

    public static final HashMap<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\img\\bg\\bg03.png"));

        map.put("my01",ImageUtil.getImage("com\\neuedu\\img\\plane\\my_01.png"));

        map.put("mb01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\mb01.png"));

        map.put("skill01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\skill.png"));


    }

    public static Image get(String key){
        return map.get(key);
    }

}
