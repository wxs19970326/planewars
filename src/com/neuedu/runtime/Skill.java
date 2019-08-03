package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Skill extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private int speed = FrameConstant.SPEED * 8;
    private Random random = new Random();

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
     * 判断技能越界
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
        }
    }

    /**
     * 判断是否打中boss
     * @param boss
     */
    public void colBossChecked(Boss boss) {
//        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())) {
//            gameFrame.skills.remove(this);
            Boss.isBossBlood = true;
            Plane.isScore = true;
        }
    }

}
