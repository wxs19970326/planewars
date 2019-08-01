package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.List;

public class EnemyBullet extends BaseSprite implements Drawable, Moveable {

    private Image[] images = {
            ImageMap.get("eb01")
    };
    private int speed = FrameConstant.SPEED * 5;

    private int type;

    public EnemyBullet() {
    }

    public EnemyBullet(int x, int y, int type) {
        super(x, y);
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[type],getX(), getY(), images[type].getWidth(null),
                images[type].getHeight(null),null);
        move();
    }

    @Override
    public void move() {
        if (type == 0) {
            setY(getY() + speed);
        }

        if (type == 1) {

        }

        outOfBounds();
    }

    /**
     * 构建敌机子弹矩形
     * @return
     */
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), images[type].getWidth(null), images[type].getHeight(null));
    }

    /**
     * 判断子弹是否越界
     */
    private void outOfBounds() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() >FrameConstant.FRAME_HEIGHT - images[type].getHeight(null)) {
            gameFrame.enemyBullets.remove(this);
        }
    }

    /**
     * 判断敌方子弹与己方飞机是否碰撞
     * @param plane
     */
    public void collisionChecking(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBullets.remove(this);
            Plane.flagBlood = true;
        }

    }

    /**
     * 判断敌方子弹与己方飞机是否碰撞
     * @param plane
     */
    public void proChecked(List<ItemEffective> itemEffective){
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (ItemEffective effective : itemEffective) {
            if (effective.getRectangle().intersects(this.getRectangle())) {
                gameFrame.enemyBullets.remove(this);
                gameFrame.itemEfftive.remove(effective);
            }
        }

    }



}
