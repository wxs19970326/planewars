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
import java.util.List;

public class Plane extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private Image imageFinally;
    //飞机方向判定
    private boolean up;

    public Image getImage() {
        return image;
    }

    private boolean right;
    private boolean down;
    private boolean left;
    //飞机速度
    private int speed = FrameConstant.SPEED * 4;

    //飞机血量
    private int blood = ImageMap.get("my01").getWidth(null);
    //己方飞机最终形态的血条
//    private int bloodFinally = ImageMap.get("my02").getWidth(null);
    public static boolean flagBlood = false;

    //飞机技能能量条
    private int energy;
    //加能量条开关
    public static boolean flagEnergy;

    //飞机等级
    public static int lv = 1;

    //飞机得分
    private int score;
    //加分开关
    public static boolean isScore;



    public Plane() {
        this(
                FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("my01").getWidth(null) / 2,
                FrameConstant.FRAME_HEIGHT - 50 - ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"),
                ImageMap.get("my02")
        );
    }

    public Plane(int x, int y, Image image, Image imageFinally) {
        super(x, y);
        this.image = image;
        this.imageFinally = imageFinally;
    }

    /**
     * 绘制飞机的方法
     * @param g
     */
    @Override
    public void draw(Graphics g) {
//        if (lv == 4){
//            g.drawImage(imageFinally,getX(), getY(), imageFinally.getWidth(null),
//                    imageFinally.getHeight(null),null);
//            g.setColor(Color.red);
//            g.drawRoundRect(getX(),getY() + imageFinally.getHeight(null),imageFinally.getWidth(null),10,5,5);
//            g.fillRoundRect(getX(),getY() + imageFinally.getHeight(null),bloodFinally,10,5,5);
//
//        }
        if (lv != 4) {
            g.drawImage(image,getX(), getY(), image.getWidth(null),
                    image.getHeight(null),null);
            g.setColor(Color.red);
            g.drawRoundRect(getX(),getY() + image.getHeight(null),image.getWidth(null),10,5,5);
            g.fillRoundRect(getX(),getY() + image.getHeight(null),blood,10,5,5);
        }
        g.setColor(Color.white);
        g.drawString("能量:",20,108);
        g.drawRoundRect(50, 102,50,7,5,5);
//        g.setColor(Color.white);
        g.fillRoundRect(50, 102,energy,7,5,5);
        move();
        setBlood();
        setEnergy();
        addScore();
        g.drawString("得分为:"+score,20, 68);
        g.drawString("当前等级:Lv"+lv,20, 88);
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
            if (energy == 50) {
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
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null) - 20) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null) - 20);
        }
    }

    /**
     * 飞机开火的方法
     */
    private void fire(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (lv == 1) {
            Bullet bullet = new Bullet(
                    getX() + image.getWidth(null) / 2 - ImageMap.get("mb01").getWidth(null) / 2,
                    getY() - ImageMap.get("mb01").getHeight(null),
                    ImageMap.get("mb01")
            );
            gameFrame.bulletList.add(bullet);
        }
        if (lv == 2) {
            for (int i = 1; i <= lv; i++) {
                Bullet bullet = new Bullet(
                        getX() + image.getWidth(null) * i / 3 - ImageMap.get("mb01").getWidth(null) / 2,
                        getY() - ImageMap.get("mb01").getHeight(null),
                        ImageMap.get("mb01")
                );
                gameFrame.bulletList.add(bullet);
            }
        }
        if (lv == 3) {
            for (int i = 0; i < lv; i++) {
                Bullet bullet = new Bullet(
                        getX() + image.getWidth(null) * i / 3 + 17 - ImageMap.get("mb01").getWidth(null) / 2,
                        getY() - ImageMap.get("mb01").getHeight(null),
                        ImageMap.get("mb01")
                );
                gameFrame.bulletList.add(bullet);
            }
        }
//        if (lv == 4) {
//            skill();
//        }
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
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (flagBlood) {
            if (lv != 4 && gameFrame.itemEfftive.size() == 0){
                blood = blood - 4;
                flagBlood = false;
            }
//            if (lv == 4) {
//                bloodFinally = bloodFinally - 5;
//                flagBlood = false;
//            }
            if (blood <= 0 && lv != 4) {
                GameFrame.gameover = true;
            }
//            if (bloodFinally <= 0 && lv == 4) {
//                GameFrame.gameover = true;
//            }
        }
    }

    /**
     * 飞机能量条
     */
    private void setEnergy(){
        if (flagEnergy && energy < 50) {
            energy = energy + 2;
            flagEnergy = false;
        }
    }

    /**
     * 飞机加分逻辑
     */
    private void addScore(){
        if (isScore) {
            score++;
            isScore = false;
        }
        if (score == 30) {
            lv = 2;
        }
        if (score == 70) {
            lv = 3;
        }

//        if (score == 120) {
//            lv = 4;
//        }
    }

    /**
     * 判断飞机是否吃到保护罩道具
     */
    public void collisionChecking(List<Item> list) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (Item item : list) {
            if (item.getRectangle().intersects(this.getRectangle())) {
                list.remove(item);
                if (gameFrame.itemEfftive.size() < 1){
                    ItemEffective effective = new ItemEffective(getX(),getY());
                    gameFrame.itemEfftive.add(effective);
                }
                System.out.println( gameFrame.itemEfftive.size());
            }
        }
    }

}
