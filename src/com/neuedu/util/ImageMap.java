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

        map.put("ep01",ImageUtil.getImage("com\\neuedu\\img\\plane\\ep01.png"));
        map.put("eb01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\eb01.png"));

        map.put("1",ImageUtil.getImage("com\\neuedu\\img\\explode\\1.png"));
        map.put("2",ImageUtil.getImage("com\\neuedu\\img\\explode\\2.png"));
        map.put("3",ImageUtil.getImage("com\\neuedu\\img\\explode\\3.png"));
        map.put("4",ImageUtil.getImage("com\\neuedu\\img\\explode\\4.png"));
        map.put("5",ImageUtil.getImage("com\\neuedu\\img\\explode\\5.png"));
        map.put("6",ImageUtil.getImage("com\\neuedu\\img\\explode\\6.png"));
        map.put("7",ImageUtil.getImage("com\\neuedu\\img\\explode\\7.png"));
        map.put("8",ImageUtil.getImage("com\\neuedu\\img\\explode\\8.png"));
        map.put("9",ImageUtil.getImage("com\\neuedu\\img\\explode\\9.png"));

        map.put("e1",ImageUtil.getImage("com\\neuedu\\img\\explode\\e1.png"));
        map.put("e2",ImageUtil.getImage("com\\neuedu\\img\\explode\\e2.png"));
        map.put("e3",ImageUtil.getImage("com\\neuedu\\img\\explode\\e3.png"));
        map.put("e4",ImageUtil.getImage("com\\neuedu\\img\\explode\\e4.png"));
        map.put("e5",ImageUtil.getImage("com\\neuedu\\img\\explode\\e5.png"));
        map.put("e6",ImageUtil.getImage("com\\neuedu\\img\\explode\\e6.png"));
        map.put("e7",ImageUtil.getImage("com\\neuedu\\img\\explode\\e7.png"));
        map.put("e8",ImageUtil.getImage("com\\neuedu\\img\\explode\\e8.png"));
        map.put("e9",ImageUtil.getImage("com\\neuedu\\img\\explode\\e9.png"));

    }

    public static Image get(String key){
        return map.get(key);
    }

}
