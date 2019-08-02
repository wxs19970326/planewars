package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class Item extends BaseSprite implements Drawable, Moveable {

    private static Random random = new Random();
    //道具向下的速度
    private int speed = FrameConstant.SPEED * 3;
    //角度
    private double speedD = 0;

    //道具类型
    private int type;

    //保护罩计步器
    private int stepPro;

    //加血道具计步器
    private int stepBlood;

    private Image[] images = {
            ImageMap.get("item01"),
            ImageMap.get("item02"),
            ImageMap.get("item03"),
            ImageMap.get("item04")

    };

    private Image[] imagesBloods = {
            ImageMap.get("blood1"),
            ImageMap.get("blood2"),
            ImageMap.get("blood3"),
            ImageMap.get("blood4")
    };

    public Item() {
    }

    public Item(int x, int y, int type) {
        super(x, y);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public void draw(Graphics g) {
        if (type == 0) {
            g.drawImage(images[stepPro / 25],getX(), getY(), images[stepPro / 25].getWidth(null),
                    images[stepPro / 25].getHeight(null),null);
            stepPro++;
            if (stepPro == 75) {
                stepPro = 0;
            }
        }
        if (type == 1) {
            g.drawImage(imagesBloods[stepBlood / 25],getX(), getY(), imagesBloods[stepBlood / 25].getWidth(null),
                    imagesBloods[stepBlood / 25].getHeight(null),null);
            stepBlood++;
            if (stepBlood == 75) {
                stepBlood = 0;
            }
        }
        move();
    }

    @Override
    public void move() {
        if (type == 0) {
            setY(getY() + 3);
        }
        if (type == 1) {
            speedD = speedD + Math.PI * 6 / 180;
            setX(getX() + (int)(3 * Math.cos(speedD)));
            setY(getY() + 2);
        }
        outOfBounds();
    }

    @Override
    public Rectangle getRectangle() {
        if (type == 0) {
            return new Rectangle(getX(), getY(), images[type].getWidth(null), images[type].getHeight(null));
        }
        if (type == 1) {
            return new Rectangle(getX(), getY(), imagesBloods[type].getWidth(null), imagesBloods[type].getHeight(null));
        }
        return null;
    }

    /**
     * 判断敌机越界
     */
    private void outOfBounds() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() > FrameConstant.FRAME_HEIGHT - images[0].getHeight(null)) {
            gameFrame.items.remove(this);
        }
    }
}
