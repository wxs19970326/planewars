package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

/**
 * 各种血条蓝条得分
 */

public class Slip extends BaseSprite implements Drawable {

    private int type;

    private static Image[] images = {
            ImageMap.get("bossblood"),
            ImageMap.get("bossblood1"),
//            ImageMap.get("planemp"),
//            ImageMap.get("planemp1")
    };

    private static Image[] imageWarning = {
            ImageMap.get("warning1"),
            ImageMap.get("warning2")
    };
    private static Image[] imageScoer = {
            ImageMap.get("warning1"),
            ImageMap.get("warning2")
    };
    private int step;
    private int timer;
    private boolean flag = true;


    public Slip(int type) {
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        Boss boss = DataStore.get("boss");
        if (type == 0) {
            g.drawImage(images[type],10, 40, FrameConstant.FRAME_WIDTH - 20,
                    32,null);
        }
        if (type == 1) {
            g.drawImage(images[type],15, 40, boss.getBlood(),
                    30,null);
        }
//        if (type == 2) {
//            g.drawImage(images[type],50, 110,100,13,null);
//        }
        if (type == 3) {
            if (flag) {
                g.drawImage(imageWarning[step / 20], FrameConstant.FRAME_WIDTH / 2 - imageWarning[step / 20].getWidth(null) / 2,
                        FrameConstant.FRAME_HEIGHT / 2 - imageWarning[step / 20].getHeight(null) / 2, imageWarning[step / 20].getWidth(null),
                        imageWarning[step / 20].getHeight(null), null);
                step++;
                if (step == 40) {
                    step = 0;
                }
                timer++;
            }
            if (timer == 160) {
                flag = false;
            }
        }
    }
}
