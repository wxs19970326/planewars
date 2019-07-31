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

    private static Image[] images = {
            ImageMap.get("ep01"),
            ImageMap.get("ep02"),
            ImageMap.get("ep03")
    };
    private int speed = FrameConstant.SPEED * 2;
    private double speedD;
    private int count;
    private Random random = new Random();

    //记录敌机容器元素个数
    private int index;

    //敌机类型
    private int type;

    public EnemyPlane() {
    }

    public EnemyPlane(int x, int y,Image[] images,int type) {
        super(x, y);
        this.images = images;
        this.type = type;

    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(images[type],getX(), getY(), images[type].getWidth(null),
                images[type].getHeight(null),null);
        move();
        fire();
    }

    @Override
    public void move() {
        if (type == 0) {
            setY(getY() + speed);
        } else if (type == 1){
            speedD = speedD + Math.PI * 6 / 180;
            setX(getX() + (int)(6 * Math.cos(speedD)));
            setY(getY() + speed * 3);
        }
        outOfBounds();
    }

    /**
     * 构建敌机矩形
     * @return
     */
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), images[type].getWidth(null), images[type].getHeight(null));
    }

    //创建敌机的方法
    public void creatPlane() {
        count++;
        if (random.nextInt(100) > 95) {
            type = 1;
        }else {
            type = 0;
        }
        GameFrame gameFrame = DataStore.get("gameFrame");
        int round = random.nextInt(FrameConstant.FRAME_WIDTH);
        if (count == 40 && index <= 20) {
            if (round <= FrameConstant.FRAME_WIDTH - ImageMap.get("ep01").getWidth(null)) {
                gameFrame.enemyPlanes.add(new EnemyPlane(
                        round,
                        -new Random().nextInt(100) * 2,
                        images,
                        type
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
        if (random.nextInt(1000) > 995 && getY() >= - images[type].getHeight(null) / 2) {
            EnemyBullet bullet = new EnemyBullet(
                    getX() + images[type].getWidth(null) / 2 - ImageMap.get("eb01").getWidth(null) / 2,
                    getY() + images[type].getHeight(null),
                    ImageMap.get("eb01"),
                    0
            );
            gameFrame.enemyBullets.add(bullet);
        }
    }

    /**
     * 判断敌机越界
     */
    private void outOfBounds() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() > FrameConstant.FRAME_HEIGHT - images[type].getHeight(null)) {
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
