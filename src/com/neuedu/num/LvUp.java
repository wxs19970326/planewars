package com.neuedu.num;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.fram.GameFrame;
import com.neuedu.runtime.Plane;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

/**
 * 升级效果
 */
public class LvUp extends BaseSprite implements Drawable, Moveable {

    private Image[] images = {
            ImageMap.get("up0"), ImageMap.get("up1"), ImageMap.get("up2"), ImageMap.get("up3"), ImageMap.get("up4"),
            ImageMap.get("up5"), ImageMap.get("up6"), ImageMap.get("up7"), ImageMap.get("up8"), ImageMap.get("up9"),
            ImageMap.get("up10"), ImageMap.get("up11"), ImageMap.get("up12"), ImageMap.get("up13"),
            ImageMap.get("up14"),
    };

    private int step;
    private boolean isUp = true;
//    public static boolean isCreat = true;


    @Override
    public void draw(Graphics g) {
        Plane plane = DataStore.get("Plane");
        if (step >= 45) {
            isUp = false;
        }

        if (isUp) {
            g.drawImage(images[step / 3], plane.getX() - 80, plane.getY() - 159, images[step / 3].getHeight(null) * 2,
                    images[step / 3].getHeight(null) * 2,null);
            step++;
        }

        if (!isUp) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.lvUps.remove(this);
        }
    }

    @Override
    public void move() {

    }
}
