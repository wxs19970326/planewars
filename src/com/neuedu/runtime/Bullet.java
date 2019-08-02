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
import java.util.Random;

public class Bullet extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private int speed = FrameConstant.SPEED * 5;
    private Random random = new Random();
    private int timer;

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
                //击中敌方飞机后，敌机减血逻辑
                enemyPlane.setHp(enemyPlane.getHp()-1);
                if (enemyPlane.getHp() <= 0) {
                    list.remove(enemyPlane);
                    //打中则添加爆炸效果
                    Explode e = new Explode(enemyPlane.getX(),enemyPlane.getY(), 0);
                    gameFrame.explodes.add(e);
                    //敌机类型为1则生成保护罩道具
                    if (enemyPlane.getType() == 1) {
                        Item item = new Item(random.nextInt(440) + 20, random.nextInt(100),0);
                        gameFrame.items.add(item);
                    }
                    //敌机类型为2则生成加血道具
                    if (enemyPlane.getType() == 2) {
                        Item itemBlood = new Item(enemyPlane.getX(),enemyPlane.getY(),1);
                        gameFrame.items.add(itemBlood);
                    }
                }

                //移除子弹
                gameFrame.bulletList.remove(this);

                //如果打中则飞机能量条增加开关变为true
                Plane.flagEnergy = true;
                //打中后飞机加分开关变为true
                Plane.isScore = true;


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

    /**
     * 判断是否打中boss
     * @param boss
     */
    public void colBossChecked(Boss boss) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())) {
            gameFrame.bulletList.remove(this);
            Boss.isBoold = true;
//            timer++;
//            if (timer == 5) {
                //如果打中则boss能量条增加开关变为true
                Plane.flagEnergy = true;
//                timer = 0;
//            }
        }
    }

}
