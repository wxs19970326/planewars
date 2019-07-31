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
    private int speed = FrameConstant.SPEED * 4;

    //飞机血量
    private int blood = ImageMap.get("my01").getWidth(null);
    public static boolean flagBlood = false;

    //飞机技能能量条
    private int energy;
    public static boolean flagEnergy;



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
        g.setColor(Color.red);
        g.drawRoundRect(getX(),getY() + image.getHeight(null),image.getWidth(null),10,5,5);
        g.fillRoundRect(getX(),getY() + image.getHeight(null),blood,10,5,5);
        g.setColor(Color.magenta);
        g.drawRoundRect(20, 45,100,10,5,5);
        g.fillRoundRect(20, 45,energy,10,5,5);
        move();
        setBlood();
        setEnergy();
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
     * 构建己方飞机矩形
     * @return
     */
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
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
            if (energy == 100) {
                skill();
                energy = 0;
            }

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
                getY() - ImageMap.get("skill01").getHeight(null) + 32,
                ImageMap.get("skill01")
        );
        gameFrame.skills.add(skill);
    }

    /**
     * 飞机血量判断
     */
    private void setBlood(){
        if (flagBlood) {
            blood = blood - 4;
            flagBlood = false;
            if (blood <= 0) {
                GameFrame.gameover = true;
            }
        }
    }

    /**
     * 飞机能量条
     */
    private void setEnergy(){
        if (flagEnergy && energy < 100) {
            energy = energy + 5;
            flagEnergy = false;
        }
    }

}
