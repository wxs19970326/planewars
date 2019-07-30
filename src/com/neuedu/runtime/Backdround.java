package com.neuedu.runtime;
import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;
import java.awt.Image;
import java.awt.Graphics;



/**
 * 背景图片
 */
public class Backdround extends BaseSprite implements Drawable, Moveable {

    private Image image;

    public Backdround() {
        this(0, -(ImageMap.get("bg01").getHeight(null) * 625 / 1000 - FrameConstant.FRAME_HEIGHT), ImageMap.get("bg01"));
    }

    public Backdround(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    /**
     * 绘制背景图片的方法
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(), getY(), image.getWidth(null) * 625 / 1000,
                image.getHeight(null) * 625 / 1000,null);
        move();
    }

    /**
     * 背景图片移动的方法
     */
    @Override
    public void move() {
        setY(getY() + FrameConstant.SPEED);
        if (getY() == 50){
            setY(-(ImageMap.get("bg01").getHeight(null) * 625 / 1000 - FrameConstant.FRAME_HEIGHT));
        }
    }


}
