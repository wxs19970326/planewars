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

    private Image[] images = {
            ImageMap.get("item01")
    };

    public Item() {
    }

    public Item(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[0],getX(), getY(), images[0].getWidth(null),
                images[0].getHeight(null),null);
        move();
    }

    @Override
    public void move() {
        setY(getY() + 3);
        outOfBounds();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), images[0].getWidth(null), images[0].getHeight(null));
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
