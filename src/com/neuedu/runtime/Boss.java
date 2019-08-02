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

public class Boss extends BaseSprite implements Drawable, Moveable {

    private int speed = FrameConstant.SPEED * 2;
    private boolean dir = false;
    private Random random = new Random();
    private int timer;
    private Image[] image = {
            ImageMap.get("boss1"), ImageMap.get("boss2"), ImageMap.get("boss3"),
            ImageMap.get("boss4"), ImageMap.get("boss5"), ImageMap.get("boss6"),
            ImageMap.get("boss7"), ImageMap.get("boss8"), ImageMap.get("boss9"),
    };
    public static boolean isLive = false;
    public static boolean isMove = true;
    private int blood = FrameConstant.FRAME_WIDTH - 30;
    //子弹打中boss减血开关
    public static boolean isBoold;
    //技能打中boss减血开关
    public static boolean isBossBlood;
    //boss减血计时器
    private int timerBlood;
    private int count;

    //角度
    private double speedD;

    private Slip slip = new Slip(0);
    private Slip slip1 = new Slip(1);

    public Boss() {
        this(
                FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("boss1").getWidth(null) / 2,
                -35 - ImageMap.get("boss1").getHeight(null)
        );
    }

    public Boss(int x, int y) {
        super(x, y);
    }

    public int getBlood() {
        return blood;
    }

    @Override
    public void draw(Graphics g) {
        fire();
        move();
        setBoold();
        timer++;
        g.drawImage(image[count],getX(), getY(), image[count].getWidth(null),
                image[count].getHeight(null),null);
        if (getY() >= 70) {
            g.setColor(Color.green);
//            g.drawRoundRect(105,55,image[count].getWidth(null),20,5,5);
//            g.fillRoundRect(105,55,blood,20,5,5);
//            g.fillRoundRect(8, 42,blood,28,20,20);
            slip1.draw(g);
            slip.draw(g);
        }
        if (timer == 2) {
            count++;
            if (count == 8) {
                count = 0;
            }
            timer = 0;
        }


    }

    @Override
    public void move() {
        if (isLive && isMove) {
            if (getY() <= 70) {
                setY(getY() + speed);
            }else {
                if (!dir) {
                    setX(getX() - speed);
                    if (getX() <= 0) {
                        dir = true;
                    }
                } else {
                    setX(getX() + speed);
                    if (getX() >= FrameConstant.FRAME_WIDTH - image[0].getWidth(null)){
                        dir = false;
                    }
                }
            }
        }else {

        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image[0].getWidth(null), image[0].getHeight(null));
    }

    /**
     *boss开枪
     */
    private void fire(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() >= 70) {
            if (random.nextInt(100) > 80) {
                speedD = speedD + Math.PI / 4;
                EnemyBullet enemyBullet = new EnemyBullet(
                        getX() + (image[0].getWidth(null) / 2) + (int)(image[1].getWidth(null) / 2 * Math.cos(speedD)),
                        getY() + image[2].getWidth(null) / 2 + (int)(image[3].getWidth(null) / 2 * Math.sin(speedD)),
                        1,
                        speedD
                );
//            enemyBullet.setSpeedD(speedD);
                gameFrame.enemyBullets.add(enemyBullet);

                if (speedD == 2 * Math.PI) {
                    speedD = 0;
                }
            }
        }
    }

    private void setBoold(){
        GameFrame gameFrame = DataStore.get("gameFrame");
//        if (blood <= 0) {
//            Explode explode = new Explode(getX(),getY(),9);
//            gameFrame.explodes.add(explode);
//            for (int i = 0; i < 35; i++) {
//
//            }
//            Explode explode1 = new Explode(getX(),getY(),9);
//            gameFrame.explodes.add(explode1);
//        }
        //子弹打中boss
        if (isBoold) {
            blood = blood - 3;
            isBoold = false;
            if (blood <= 0) {
//                isLive = false;
                isMove = false;
                Explode explode = new Explode(getX(),getY(),9);
                gameFrame.explodes.add(explode);

            }
        }

        //技能打中boss
        if (isBossBlood) {
            timerBlood++;
            if (timerBlood == 5) {
                blood = blood - 1;
                isBossBlood = false;
                timerBlood = 0;
                if (blood <= 0) {
//                    isLive = false;
                    isMove = false;
                    Explode explode1 = new Explode(getX(),getY(),9);
                    gameFrame.explodes.add(explode1);
                }
            }
        }
    }


}
