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

public class EnemyPlane extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private int speed = FrameConstant.SPEED * 2;
    private int count;
    private Random random = new Random();

    private int index;

    public EnemyPlane() {
        this(0,0,ImageMap.get("eb01"));
    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(), getY(), image.getWidth(null),
                image.getHeight(null),null);
        move();
        fire();
    }

    @Override
    public void move() {
        setY(getY() + speed);
        outOfBounds();
    }

    /**
     * 构建敌机矩形
     * @return
     */
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //创建敌机的方法
    public void creatPlane() {
        count++;
        GameFrame gameFrame = DataStore.get("gameFrame");
        int round = random.nextInt(FrameConstant.FRAME_WIDTH);
        if (count == 50 && index <= 15) {
            if (round <= FrameConstant.FRAME_WIDTH - ImageMap.get("ep01").getWidth(null)) {
                gameFrame.enemyPlanes.add(new EnemyPlane(
                        round,
                        -new Random().nextInt(100) * 2,
                        ImageMap.get("ep01")
                ));
            }
            index = gameFrame.enemyPlanes.size();
            count = 0;
        }

    }

    /**
     * 敌机发射子弹
     */
    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 990 && getY() >= - image.getHeight(null) / 2) {
            EnemyBullet bullet = new EnemyBullet(
                    getX() + image.getWidth(null) / 2 - ImageMap.get("eb01").getWidth(null) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("eb01")
            );
            gameFrame.enemyBullets.add(bullet);
        }
    }

    /**
     * 判断敌机越界
     */
    private void outOfBounds() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null)) {
            gameFrame.enemyPlanes.remove(this);
        }
    }

    /**
     * 判断敌机与我方飞机是否碰撞
     * @param plane
     */
    public void collisionChecking(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyPlanes.remove(this);
            Plane.flagBlood = true;

        }
    }


}
