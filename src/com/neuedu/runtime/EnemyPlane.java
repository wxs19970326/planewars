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
    //记录速度
    private int speed = FrameConstant.SPEED * 2;
    private double speedD;

    //计数器
    private int count;
    private Random random = new Random();

    //记录敌机容器元素个数
    private int index;

    //飞机血量
    private int hp;

    //敌机类型
    private int type;

    private boolean right;
//    private boolean left;

    public EnemyPlane() {
    }

    public EnemyPlane(int x, int y,Image[] images,int type) {
        super(x, y);
        this.images = images;
        this.type = type;
        init();

    }

    /**
     * 初始化不同飞机在己方飞机等级不同时的血量
     */
    private void init(){
        if (type == 0) {
            if (Plane.lv == 1) {
                hp = 2;
            } else if (Plane.lv == 2) {
                hp = 4;
            } else {
                hp = 6;
            }
        } else if (type == 1) {
            if (Plane.lv == 1) {
                hp = 1;
            } else if (Plane.lv == 2) {
                hp = 2;
            } else {
                hp = 4;
            }
        } else {
            if (Plane.lv == 1) {
                hp = 4;
            } else if (Plane.lv == 2) {
                hp = 5;
            } else {
                hp = 6;
            }
        }

    }

    public int getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * 敌机绘制方法
     * @param g
     */
    @Override
    public void draw(Graphics g) {

        g.drawImage(images[type],getX(), getY(), images[type].getWidth(null),
                images[type].getHeight(null),null);
        move();
        fire();
    }

    /**
     * 敌机移动方法
     */
    @Override
    public void move() {
        if (type == 0) {
            setY(getY() + speed);
        } else if (type == 1){
            speedD = speedD + Math.PI * 6 / 180;
            setX(getX() + (int)(6 * Math.cos(speedD)));
            setY(getY() + speed * 2);
        } else {
//            speedD = speedD + Math.PI * 6 / 180;
//            setX(getX() + (int)(3 * Math.cos(speedD)));
//            setY(getY() + speed);
            if (!right) {
                setX(getX() - speed);
                if (getX() <= 0) {
                    right = true;
                }
            }
            if (right) {
                setX(getX() + speed);
            }
            setY(getY() + speed * 2);
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
        if (!GameFrame.pass) {
            count++;
            if (random.nextInt(100) > 80) {
                type = 1;
            }else if (random.nextInt(100) < 13){
                type = 2;
            } else {
                type = 0;
            }
            GameFrame gameFrame = DataStore.get("gameFrame");
            int round = random.nextInt(FrameConstant.FRAME_WIDTH);
            if (count == 30 && index <= 30) {
                if (round <= FrameConstant.FRAME_WIDTH - ImageMap.get("ep03").getWidth(null) && round >= 50) {
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
            hp--;
            if (hp <= 0) {
                gameFrame.enemyPlanes.remove(this);
            }
            Plane.flagBlood = true;

        }
    }

}
