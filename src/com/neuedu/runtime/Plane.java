package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends BaseSprite implements Moveable, Drawable {

    private Image image;
    //飞机方向判定
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;
    //飞机速度
    private int speed = FrameConstant.SPEED * 2;

    //飞机开火方式
    private boolean fire1;
    private boolean fire2;

    public Plane() {
        this(
                FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("my01").getWidth(null) / 2,
                FrameConstant.FRAME_HEIGHT - 30 - ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01")
        );
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    /**
     * 绘制飞机的方法
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(), getY(), image.getWidth(null),
                image.getHeight(null),null);
        move();
    }

    /**
     * 飞机移动方法
     */
    @Override
    public void move() {
        if (left) {
            setX(getX() - speed);
        }
        if (right) {
            setX(getX() + speed);
        }
        if (up) {
            setY(getY() - speed);
        }
        if (down) {
            setY(getY() + speed);
        }
        outOfBounds();

    }

    /**
     * 确定飞机方向的方法
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }


    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire();
        }
        if (e.getKeyCode() == KeyEvent.VK_K){
            skill();
        }
    }

    /**
     * 判断飞机是否越界
     */
    private void outOfBounds() {
        if (getX() < 5){
            setX(5);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null) - 5) {
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null) - 5);
        }
        if (getY() < 40) {
            setY(40);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null) - 5) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null) - 5);
        }
    }

    /**
     * 飞机开火的方法
     */
    private void fire(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        Bullet bullet = new Bullet(
                getX() + image.getWidth(null) / 2 - ImageMap.get("mb01").getWidth(null) / 2,
                getY() - ImageMap.get("mb01").getHeight(null),
                ImageMap.get("mb01")
        );
        gameFrame.bulletList.add(bullet);
    }
    /**
     * 飞机技能
     */
    private void skill(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        Skill skill = new Skill(
                getX() + image.getWidth(null) / 2 - ImageMap.get("skill01").getWidth(null) / 2,
                getY() - ImageMap.get("skill01").getHeight(null),
                ImageMap.get("skill01")
        );
        gameFrame.skills.add(skill);
    }

}
