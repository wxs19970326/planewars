package com.neuedu.util;

import java.awt.Image;
import java.util.HashMap;

public class ImageMap {

    public static final HashMap<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\img\\bg\\bg01.jpg"));

        map.put("my01",ImageUtil.getImage("com\\neuedu\\img\\plane\\my_01.png"));
        map.put("my02",ImageUtil.getImage("com\\neuedu\\img\\plane\\myplane01.png"));

        map.put("mb01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\mb01.png"));

        map.put("skill01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\skill.png"));

        map.put("ep01",ImageUtil.getImage("com\\neuedu\\img\\plane\\ep01.png"));
        map.put("ep02",ImageUtil.getImage("com\\neuedu\\img\\plane\\ep02.png"));
        map.put("ep03",ImageUtil.getImage("com\\neuedu\\img\\plane\\ep03.png"));
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

        map.put("item01",ImageUtil.getImage("com\\neuedu\\img\\item\\item01.png"));
        map.put("eff01",ImageUtil.getImage("com\\neuedu\\img\\item\\protect.png"));

        map.put("boss1",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_01.png"));
        map.put("boss2",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_02.png"));
        map.put("boss3",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_03.png"));
        map.put("boss4",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_04.png"));
        map.put("boss5",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_05.png"));
        map.put("boss6",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_06.png"));
        map.put("boss7",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_07.png"));
        map.put("boss8",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_08.png"));
        map.put("boss9",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_09.png"));

    }

    public static Image get(String key){
        return map.get(key);
    }

}
