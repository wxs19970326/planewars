package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;

import java.awt.*;
import java.util.List;

public class Skill extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private int speed = FrameConstant.SPEED * 8;

    public Skill() {
    }

    public Skill(int x, int y, Image image) {
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
     * 构建技能矩形
     * @return
     */
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    /**
     * 判断子弹越界
     */
    private void outOfBounds() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() < 45) {
            gameFrame.skills.remove(this);
        }
    }

    /**
     * 判断我方技能与敌方飞机是否碰撞
     * @param plane
     */
    public void collisionChecking(List<EnemyPlane> list) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : list) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                gameFrame.enemyPlanes.remove(enemyPlane);
                Explode e = new Explode(enemyPlane.getX(),enemyPlane.getY(), 0);
                gameFrame.explodes.add(e);

                //打中后飞机加分开关变为true
                Plane.isScore = true;
            }
        }
    }
}
