package com.neuedu.util;

import java.awt.Image;
import java.util.HashMap;

public class ImageMap {

    private static final HashMap<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\img\\bg\\bg01.jpg"));

        map.put("my01",ImageUtil.getImage("com\\neuedu\\img\\plane\\my_01.png"));
        map.put("my02",ImageUtil.getImage("com\\neuedu\\img\\plane\\myplane01.png"));

        /**
         * m、h、p
         */
        map.put("m",ImageUtil.getImage("com\\neuedu\\img\\plane\\m.png"));
        map.put("h",ImageUtil.getImage("com\\neuedu\\img\\plane\\h.png"));
        map.put("p",ImageUtil.getImage("com\\neuedu\\img\\plane\\p.png"));

        /**
         * 己方子弹
         */
        map.put("mb01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\mb01.png"));

        /**
         * 技能
         */
        map.put("skill01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\skill.png"));

        /**
         *敌方飞机
         */
        map.put("ep01",ImageUtil.getImage("com\\neuedu\\img\\plane\\ep01.png"));
        map.put("ep02",ImageUtil.getImage("com\\neuedu\\img\\plane\\ep02.png"));
        map.put("ep03",ImageUtil.getImage("com\\neuedu\\img\\plane\\ep03.png"));
        map.put("eb01",ImageUtil.getImage("com\\neuedu\\img\\bullet\\eb01.png"));
        map.put("eb02",ImageUtil.getImage("com\\neuedu\\img\\bullet\\eb02.png"));
        map.put("eb03",ImageUtil.getImage("com\\neuedu\\img\\bullet\\eb03.png"));

        /**
         * boss爆炸
         */
        map.put("1",ImageUtil.getImage("com\\neuedu\\img\\explode\\1.png"));
        map.put("2",ImageUtil.getImage("com\\neuedu\\img\\explode\\2.png"));
        map.put("3",ImageUtil.getImage("com\\neuedu\\img\\explode\\3.png"));
        map.put("4",ImageUtil.getImage("com\\neuedu\\img\\explode\\4.png"));
        map.put("5",ImageUtil.getImage("com\\neuedu\\img\\explode\\5.png"));
        map.put("6",ImageUtil.getImage("com\\neuedu\\img\\explode\\6.png"));
        map.put("7",ImageUtil.getImage("com\\neuedu\\img\\explode\\7.png"));
        map.put("8",ImageUtil.getImage("com\\neuedu\\img\\explode\\8.png"));
        map.put("9",ImageUtil.getImage("com\\neuedu\\img\\explode\\9.png"));

        /**
         * 敌机爆炸
         */
        map.put("e1",ImageUtil.getImage("com\\neuedu\\img\\explode\\e1.png"));
        map.put("e2",ImageUtil.getImage("com\\neuedu\\img\\explode\\e2.png"));
        map.put("e3",ImageUtil.getImage("com\\neuedu\\img\\explode\\e3.png"));
        map.put("e4",ImageUtil.getImage("com\\neuedu\\img\\explode\\e4.png"));
        map.put("e5",ImageUtil.getImage("com\\neuedu\\img\\explode\\e5.png"));
        map.put("e6",ImageUtil.getImage("com\\neuedu\\img\\explode\\e6.png"));
        map.put("e7",ImageUtil.getImage("com\\neuedu\\img\\explode\\e7.png"));
        map.put("e8",ImageUtil.getImage("com\\neuedu\\img\\explode\\e8.png"));
        map.put("e9",ImageUtil.getImage("com\\neuedu\\img\\explode\\e9.png"));

        /**
         * 保护罩道具
         */
        map.put("item01",ImageUtil.getImage("com\\neuedu\\img\\item\\adddefense1.png"));
        map.put("item02",ImageUtil.getImage("com\\neuedu\\img\\item\\adddefense2.png"));
        map.put("item03",ImageUtil.getImage("com\\neuedu\\img\\item\\adddefense3.png"));
        map.put("item04",ImageUtil.getImage("com\\neuedu\\img\\item\\adddefense4.png"));
        map.put("eff01",ImageUtil.getImage("com\\neuedu\\img\\item\\protect.png"));

        /**
         * 血道具
         */
        map.put("blood1",ImageUtil.getImage("com\\neuedu\\img\\item\\addHP1.png"));
        map.put("blood2",ImageUtil.getImage("com\\neuedu\\img\\item\\addHP2.png"));
        map.put("blood3",ImageUtil.getImage("com\\neuedu\\img\\item\\addHP3.png"));
        map.put("blood4",ImageUtil.getImage("com\\neuedu\\img\\item\\addHP4.png"));

        map.put("boss1",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_01.png"));
        map.put("boss2",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_02.png"));
        map.put("boss3",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_03.png"));
        map.put("boss4",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_04.png"));
        map.put("boss5",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_05.png"));
        map.put("boss6",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_06.png"));
        map.put("boss7",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_07.png"));
        map.put("boss8",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_08.png"));
        map.put("boss9",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_A_09.png"));
        map.put("bossblood",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_blood.png"));
        map.put("bossblood1",ImageUtil.getImage("com\\neuedu\\img\\boss\\boss_blood1.png"));

        /**
         * 开始图
         */
        map.put("three0",ImageUtil.getImage("com\\neuedu\\img\\start\\0.png"));
        map.put("three1",ImageUtil.getImage("com\\neuedu\\img\\start\\1.png"));
        map.put("three2",ImageUtil.getImage("com\\neuedu\\img\\start\\2.png"));
        map.put("three3",ImageUtil.getImage("com\\neuedu\\img\\start\\3.png"));
        map.put("three4",ImageUtil.getImage("com\\neuedu\\img\\start\\4.png"));
        map.put("three5",ImageUtil.getImage("com\\neuedu\\img\\start\\5.png"));
        map.put("two6",ImageUtil.getImage("com\\neuedu\\img\\start\\6.png"));
        map.put("two7",ImageUtil.getImage("com\\neuedu\\img\\start\\7.png"));
        map.put("two8",ImageUtil.getImage("com\\neuedu\\img\\start\\8.png"));
        map.put("two9",ImageUtil.getImage("com\\neuedu\\img\\start\\9.png"));
        map.put("two10",ImageUtil.getImage("com\\neuedu\\img\\start\\10.png"));
        map.put("two11",ImageUtil.getImage("com\\neuedu\\img\\start\\11.png"));
        map.put("one12",ImageUtil.getImage("com\\neuedu\\img\\start\\12.png"));
        map.put("one13",ImageUtil.getImage("com\\neuedu\\img\\start\\13.png"));
        map.put("one14",ImageUtil.getImage("com\\neuedu\\img\\start\\14.png"));
        map.put("one15",ImageUtil.getImage("com\\neuedu\\img\\start\\15.png"));
        map.put("one16",ImageUtil.getImage("com\\neuedu\\img\\start\\16.png"));
        map.put("one17",ImageUtil.getImage("com\\neuedu\\img\\start\\17.png"));
        map.put("start0",ImageUtil.getImage("com\\neuedu\\img\\start\\start0.png"));
        map.put("start1",ImageUtil.getImage("com\\neuedu\\img\\start\\start1.png"));
        map.put("start2",ImageUtil.getImage("com\\neuedu\\img\\start\\start2.png"));
        map.put("start3",ImageUtil.getImage("com\\neuedu\\img\\start\\start3.png"));
        map.put("start4",ImageUtil.getImage("com\\neuedu\\img\\start\\start4.png"));
        map.put("start5",ImageUtil.getImage("com\\neuedu\\img\\start\\start5.png"));
        map.put("start6",ImageUtil.getImage("com\\neuedu\\img\\start\\start6.png"));


        map.put("warning1",ImageUtil.getImage("com\\neuedu\\img\\num\\warning.png"));
        map.put("warning2",ImageUtil.getImage("com\\neuedu\\img\\num\\warning1.png"));

        map.put("score0",ImageUtil.getImage("com\\neuedu\\img\\score\\0.png"));
        map.put("score1",ImageUtil.getImage("com\\neuedu\\img\\score\\1.png"));
        map.put("score2",ImageUtil.getImage("com\\neuedu\\img\\score\\2.png"));
        map.put("score3",ImageUtil.getImage("com\\neuedu\\img\\score\\score.png"));

        /**
         * win
         */
        map.put("win1",ImageUtil.getImage("com\\neuedu\\img\\win\\0.png"));
        map.put("win2",ImageUtil.getImage("com\\neuedu\\img\\win\\1.png"));
        map.put("win4",ImageUtil.getImage("com\\neuedu\\img\\win\\3.png"));
        map.put("win5",ImageUtil.getImage("com\\neuedu\\img\\win\\4.png"));
        map.put("win6",ImageUtil.getImage("com\\neuedu\\img\\win\\5.png"));
        map.put("win7",ImageUtil.getImage("com\\neuedu\\img\\win\\6.png"));
        map.put("win8",ImageUtil.getImage("com\\neuedu\\img\\win\\7.png"));
        map.put("win9",ImageUtil.getImage("com\\neuedu\\img\\win\\8.png"));
        map.put("win10",ImageUtil.getImage("com\\neuedu\\img\\win\\9.png"));
        map.put("win11",ImageUtil.getImage("com\\neuedu\\img\\win\\10.png"));
        map.put("win12",ImageUtil.getImage("com\\neuedu\\img\\win\\11.png"));
        map.put("win13",ImageUtil.getImage("com\\neuedu\\img\\win\\12.png"));
        map.put("win14",ImageUtil.getImage("com\\neuedu\\img\\win\\13.png"));
        map.put("win15",ImageUtil.getImage("com\\neuedu\\img\\win\\14.png"));

        /**
         * gameover
         */
        map.put("over0",ImageUtil.getImage("com\\neuedu\\img\\gameover\\0.png"));
        map.put("over1",ImageUtil.getImage("com\\neuedu\\img\\gameover\\1.png"));
        map.put("over2",ImageUtil.getImage("com\\neuedu\\img\\gameover\\2.png"));
        map.put("over3",ImageUtil.getImage("com\\neuedu\\img\\gameover\\3.png"));
        map.put("over4",ImageUtil.getImage("com\\neuedu\\img\\gameover\\4.png"));
        map.put("over5",ImageUtil.getImage("com\\neuedu\\img\\gameover\\5.png"));
        map.put("over6",ImageUtil.getImage("com\\neuedu\\img\\gameover\\6.png"));
        map.put("over7",ImageUtil.getImage("com\\neuedu\\img\\gameover\\7.png"));
        map.put("over8",ImageUtil.getImage("com\\neuedu\\img\\gameover\\8.png"));
        map.put("over9",ImageUtil.getImage("com\\neuedu\\img\\gameover\\9.png"));
        map.put("over10",ImageUtil.getImage("com\\neuedu\\img\\gameover\\10.png"));
        map.put("over11",ImageUtil.getImage("com\\neuedu\\img\\gameover\\11.png"));
        map.put("over12",ImageUtil.getImage("com\\neuedu\\img\\gameover\\12.png"));
        map.put("over13",ImageUtil.getImage("com\\neuedu\\img\\gameover\\13.png"));

        /**
         * score
         */
        map.put("00",ImageUtil.getImage("com\\neuedu\\img\\num\\0.png"));
        map.put("01",ImageUtil.getImage("com\\neuedu\\img\\num\\1.png"));
        map.put("02",ImageUtil.getImage("com\\neuedu\\img\\num\\2.png"));
        map.put("03",ImageUtil.getImage("com\\neuedu\\img\\num\\3.png"));
        map.put("04",ImageUtil.getImage("com\\neuedu\\img\\num\\4.png"));
        map.put("05",ImageUtil.getImage("com\\neuedu\\img\\num\\5.png"));
        map.put("06",ImageUtil.getImage("com\\neuedu\\img\\num\\6.png"));
        map.put("07",ImageUtil.getImage("com\\neuedu\\img\\num\\7.png"));
        map.put("08",ImageUtil.getImage("com\\neuedu\\img\\num\\8.png"));
        map.put("09",ImageUtil.getImage("com\\neuedu\\img\\num\\9.png"));

        /**
         * 升级效果图
         */
        map.put("up0",ImageUtil.getImage("com\\neuedu\\img\\up\\0.png"));
        map.put("up1",ImageUtil.getImage("com\\neuedu\\img\\up\\90.png"));
        map.put("up2",ImageUtil.getImage("com\\neuedu\\img\\up\\180.png"));
        map.put("up3",ImageUtil.getImage("com\\neuedu\\img\\up\\270.png"));
        map.put("up4",ImageUtil.getImage("com\\neuedu\\img\\up\\360.png"));
        map.put("up5",ImageUtil.getImage("com\\neuedu\\img\\up\\450.png"));
        map.put("up6",ImageUtil.getImage("com\\neuedu\\img\\up\\540.png"));
        map.put("up7",ImageUtil.getImage("com\\neuedu\\img\\up\\630.png"));
        map.put("up8",ImageUtil.getImage("com\\neuedu\\img\\up\\720.png"));
        map.put("up9",ImageUtil.getImage("com\\neuedu\\img\\up\\810.png"));
        map.put("up10",ImageUtil.getImage("com\\neuedu\\img\\up\\900.png"));
        map.put("up11",ImageUtil.getImage("com\\neuedu\\img\\up\\990.png"));
        map.put("up12",ImageUtil.getImage("com\\neuedu\\img\\up\\1080.png"));
        map.put("up13",ImageUtil.getImage("com\\neuedu\\img\\up\\1170.png"));
        map.put("up14",ImageUtil.getImage("com\\neuedu\\img\\up\\1260.png"));

    }

    public static Image get(String key){
        return map.get(key);
    }

}
