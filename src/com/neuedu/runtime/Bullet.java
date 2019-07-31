package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Bullet extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private int speed = FrameConstant.SPEED * 5;

    public Bullet() {
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(), getY(), image.getWidth(null),
                image.getHeight(null),null);
        move();
    }

    @Override
    public void move() {
        setY(getY() - speed);
        outOfBounds();
    }

    /**
     * 构建子弹矩形
     * @return
     */
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    /**
     * 判断我方子弹与敌方是否撞击
     * @param list
     */
    public void collisionChecking(List<EnemyPlane> list) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : list) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                list.remove(enemyPlane);
                gameFrame.bulletList.remove(this);

                //如果打中则飞机能量条增加开关变为true
                Plane.flagEnergy = true;
                //打中后飞机加分开关变为true
                Plane.isScore = true;

                //打中则添加爆炸效果
                Explode e = new Explode(enemyPlane.getX(),enemyPlane.getY());
                gameFrame.explodes.add(e);
            }
        }
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
