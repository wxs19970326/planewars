package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Hero extends BaseSprite implements Drawable, Moveable {


    private Image image;
    private int dir;

    public Hero() {
        this(120, FrameConstant.FRAME_HEIGHT - 170, ImageMap.get("hero01"));
        dir = -image.getWidth(null);
    }

    public Hero(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(image, getX(),getY(), dir, image.getHeight(null), null);
    }

    @Override
    public void move() {

    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                setX(getX() + 1);
                dir = -Math.abs(dir);
                break;
            case KeyEvent.VK_A:
                setX(getX() - 1);
                dir = Math.abs(dir);
                break;
        }
    }

}
