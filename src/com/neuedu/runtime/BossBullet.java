package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;

import java.awt.*;

public class BossBullet extends BaseSprite implements Drawable, Moveable {
    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void move() {

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),0,0);
    }

    /**
     * 判断子弹越界
     */
    private void outOfBounds() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() < 40) {
            gameFrame.bulletList.remove(this);
        }
//        System.out.println(gameFrame.bulletList.size());
    }

}
