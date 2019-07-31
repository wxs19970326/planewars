package com.neuedu.fram;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    //游戏开关
    public static boolean gameover;
    //游戏胜利开关
    public static boolean pass;

    private static Random random = new Random();

    //背景
    private Backdround bg = new Backdround();
    private Plane plane = new Plane();
    private EnemyPlane enemyPlane = new EnemyPlane();
    private Boss boss = new Boss();

    //存放子弹的集合
    public List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //存放技能集合
    public List<Skill> skills = new CopyOnWriteArrayList<>();

    //存放敌方飞机的集合
    public List<EnemyPlane> enemyPlanes = new CopyOnWriteArrayList<>();

    //存放敌方飞机的子弹
    public List<EnemyBullet> enemyBullets = new CopyOnWriteArrayList<>();

    //存放爆炸的集合
    public List<Explode> explodes = new CopyOnWriteArrayList<>();

    //存放道具
    public List<Item> items = new CopyOnWriteArrayList<>();

    //存放道具效果
    public List<ItemEffective> itemEfftive = new CopyOnWriteArrayList<>();


    public GameFrame(){
        init();
    }

    @Override
    public void paint(Graphics g) {
        bg.draw(g);
        if (!gameover) {
            //遍历敌方飞机一一画出来
            for (EnemyPlane enemyPlane : enemyPlanes) {
                enemyPlane.draw(g);
                enemyPlane.collisionChecking(plane);
            }

            if (!Boss.isLive && !pass) {
                enemyPlane.creatPlane();
            } else if (Boss.isLive){
                boss.draw(g);
            }
            plane.draw(g);
            plane.collisionChecking(items);

//            System.out.println(bulletList.size());
            //遍历子弹容器一一画出来
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
                bullet.collisionChecking(enemyPlanes);
                bullet.colBossChecked(boss);
            }

            //遍历技能集合一一画出来
            for (Skill skill : skills) {
                skill.draw(g);
                skill.collisionChecking(enemyPlanes);
            }



            //遍历敌方子弹一一画出来
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.draw(g);
                enemyBullet.collisionChecking(plane);
                enemyBullet.proChecked(itemEfftive);
            }

            for (Explode explode : explodes) {
                explode.draw(g);
            }
//            addItem();
            for (Item item : items) {
                item.draw(g);
            }
            for (ItemEffective effective : itemEfftive) {
                effective.draw(g);
            }



//        System.out.println(bulletList.size());
        }

    }

    /**
     * 生成道具
     */

//    private void addItem(){
//        if (random.nextInt(1000) > 997) {
//            Item item = new Item(random.nextInt(440) + 20, random.nextInt(100));
//            items.add(item);
//        }
//    }

    private void init(){
        DataStore.put("Plane",plane);
        //设置窗口尺寸
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        //设置居中
        setLocationRelativeTo(null);

        //设置禁止输入法
        enableInputMethods(false);

        //添加关闭监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        //刷新
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        /**
         * 添加键盘监听器
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });

        setVisible(true);
    }



    /**
     * 双缓冲
     */
    Image backImg=null;
    //重写update（）方法，在窗口的里层添加一个虚拟的图片,避免图片出现闪烁
    @Override
    public void update (Graphics g){
        //backImg.getGraphics();
        if (backImg==null){
            //如果虚拟图片不存在，创建一个和窗口一样大小的图片
            backImg=createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        }
        //获取到虚拟图片的画笔
        Graphics backg=backImg.getGraphics();
        Color c=backg.getColor();
        backg.setColor(Color.WHITE);
        backg.fillRect(0, 0, FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        backg.setColor(c);
        //调用虚拟图片的paint（）方法，每50ms刷新一次呢
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }

}
