package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.num.GameOver;
import com.neuedu.num.LvUp;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

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
     private Random random = new Random();

    //飞机血量
    public static int blood = 99;
    //己方飞机最终形态的血条
//    private int bloodFinally = ImageMap.get("my02").getWidth(null);
    public static boolean flagBlood = false;

    //飞机技能能量条
    private int energy = 99;
    //加能量条开关
    public static boolean flagEnergy;

    //飞机等级
    public static int lv = 1;

    //飞机得分
    private int score;
    //加分开关
    public static boolean isScore;

    private Boss boss = DataStore.get("boss");

    private int stepLvUp1;
    private boolean stepLvUp2;

//    //蓝条
//    private Slip slip = new Slip(2);
//    private Slip slip1 = new Slip(3);

    private MusicPlayer musicPlayer;


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

    public int getEnergy() {
        return energy;
    }

    public int getBlood() {
        return blood;
    }

    public int getScore() {
        return score;
    }

    /**
     * 绘制飞机的方法
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        if (blood >= 0) {
            move();
        }
        setBlood();
        setEnergy();
        addScore();
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
//            g.drawRoundRect(getX(),getY() + image.getHeight(null),image.getWidth(null),10,5,5);
//            g.fillRoundRect(getX(),getY() + image.getHeight(null),blood,10,5,5);
        }
        g.setColor(new Color(177, 255, 102));
        Font font = new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,18);
        g.setFont(font);


        g.setColor(Color.white);
        /**
         * MP和HP字符串
         */
        g.drawString("MP:",20,90);
        g.drawString("HP:",20,110);

        /**
         * 蓝条和血条
         */
        g.drawRoundRect(50, 79,100,11,5,5);
        g.drawRoundRect(50,98,100,11,5,5);
        //蓝
        g.setColor(Color.blue);
        g.fillRoundRect(51, 80,energy,10,5,5);
        //血
        g.setColor(Color.red);
        g.fillRoundRect(51,99,blood,10,5,5);

        g.setColor(Color.white);
        g.drawString("当前等级:",20, 143);
        g.drawString("Lv"+lv,100, 143);
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
            if (blood >= 0) {
                fire();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_K){
            if (energy == 99) {
                if (blood >= 0) {
                    skill();
                }
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
        //我方飞机音效
//        play(0);
//        musicPlayer = null;
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
        //播放技能音效
        play(7);
        musicPlayer = null;
    }

    /**
     * 飞机血量判断
     */
    private void setBlood(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (flagBlood) {
            if (lv != 4 && gameFrame.itemEfftive.size() == 0){
                blood = blood - 5;
                flagBlood = false;
            }
//            if (lv == 4) {
//                bloodFinally = bloodFinally - 5;
//                flagBlood = false;
//            }
            if (blood <= 0 && lv != 4) {
                GameFrame.gameover = true;
                GameOver gameOver = new GameOver();
                gameFrame.gameOvers.add(gameOver);
                play(6);
                musicPlayer = null;
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
        if (flagEnergy && energy < 99) {
            energy = energy + 3;
            flagEnergy = false;
        }
    }

    /**
     * 飞机加分逻辑
     */
    private void addScore(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (isScore) {
            score = score + random.nextInt(10) + 10;
            isScore = false;
        }
        if (score >= 2000 && score <= 3500) {
            if (!stepLvUp2) {
                stepLvUp1++;
            }
            lv = 2;
            //升级效果
            if (gameFrame.lvUps.size() < 1 && stepLvUp1 == 1) {
                LvUp lv = new LvUp();
                gameFrame.lvUps.add(lv);
                play(3);
                musicPlayer = null;
                stepLvUp2 = true;
                stepLvUp1 = 0;
            }
        }
        if (score >= 5000) {
            if (stepLvUp2) {
                stepLvUp1++;
            }
            lv = 3;
            //升级效果
            if (gameFrame.lvUps.size() < 1 && stepLvUp1 == 1) {
                LvUp lv = new LvUp();
                gameFrame.lvUps.add(lv);
                play(3);
                musicPlayer = null;
                stepLvUp2 = false;
                stepLvUp1 = 0;
            }

        }

//        if (score == 120) {
//            lv = 4;
//        }
    }

    /**
     * 添加音效方法
     * @param type 音效类型
     */
    private void play(int type){
        musicPlayer = new MusicPlayer(type);
        musicPlayer.start();
    }

    /**
     * 判断飞机是否吃到保护罩道具
     */
    public void collisionChecking(List<Item> list) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (Item item : list) {
            if (item.getRectangle().intersects(this.getRectangle())) {
                list.remove(item);
                //如果已经有保护罩道具效果，如果再吃道具就没有保护罩
                if (item.getType() == 0) {
                    if (gameFrame.itemEfftive.size() < 1){
                        ItemEffective effective = new ItemEffective(getX(),getY());
                        gameFrame.itemEfftive.add(effective);
                        play(2);
                        musicPlayer = null;
                    }
                }

                //加血道具实现加血效果
                if (item.getType() == 1) {
                    play(2);
                    musicPlayer = null;
                    if (this.blood > ImageMap.get("my01").getWidth(null) - 4) {
                        this.blood = ImageMap.get("my01").getWidth(null);

                    } else {
                        this.blood = blood + 5;
                    }
                }
            }
        }
    }

}
