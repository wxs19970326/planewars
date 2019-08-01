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

    private Image[] images = {
            ImageMap.get("item01"),
            ImageMap.get("blood1")
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
        g.drawImage(images[type],getX(), getY(), images[type].getWidth(null),
                images[type].getHeight(null),null);
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
            setY(getY() + 3);
        }
        outOfBounds();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), images[type].getWidth(null), images[type].getHeight(null));
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
