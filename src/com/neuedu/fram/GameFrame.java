package com.neuedu.fram;

import com.neuedu.constant.FrameConstant;
import com.neuedu.num.GameOver;
import com.neuedu.num.LvUp;
import com.neuedu.num.Number;
import com.neuedu.num.Start;
import com.neuedu.runtime.*;
import com.neuedu.util.DataStore;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    //游戏开关
    public static boolean gameover = true;
    //游戏胜利开关
    public static boolean pass = false;

    private static Random random = new Random();

    //背景
    private Backdround bg = new Backdround();
    //己方飞机
    private Plane plane = new Plane();
    //敌方飞机
    private EnemyPlane enemyPlane = new EnemyPlane();
    //boss
    private Boss boss = new Boss();
    //boss血条
    private Slip slipBoss = new Slip(3);
    //分
    private Slip slipScore = new Slip(4);


    private Number number = new Number();

//    private Slip slipWin = new Slip(5);

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

    //开场动画和开始打boss动画
    public List<Start> starts = new CopyOnWriteArrayList<>();
    //
    public List<Slip> slips = new CopyOnWriteArrayList<>();

    //游戏结束动画
    public List<GameOver> gameOvers = new CopyOnWriteArrayList<>();
    public List<LvUp> lvUps = new CopyOnWriteArrayList<>();
    //音乐
    private MusicPlayer musicPlayer;
    private boolean isPlay = true;
    private boolean isPlay1 = true;

    public GameFrame(){
        init();
    }

    @Override
    public void paint(Graphics g) {
        bg.draw(g);

        if (gameover){
            for (Start start : starts) {
                start.draw(g);
            }

            //游戏开始音效
            /*if (starts.isEmpty() && isPlay1) {
                play(8);
                musicPlayer = null;
                isPlay1 = false;
            }*/

            /**
             * 当游戏没开始时的画面是己方飞机和分数血条等
             */
            plane.draw(g);
            //画分数
            for (Slip slip : slips) {
                slip.draw(g);
            }
            number.draw(g);
            if (plane.getBlood() <= 0) {
                for (GameOver gameOver : gameOvers) {
                    gameOver.draw(g);
                }
            }
        }


        if (!gameover) {
            //遍历敌方子弹一一画出来
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.draw(g);
                enemyBullet.collisionChecking(plane);
                enemyBullet.proChecked(itemEfftive);
            }
            //若果boss没有存活并且通关状态为false,那么创建敌方飞机
            if (!Boss.isLive && !pass) {
                enemyPlane.creatPlane();
            } else if (Boss.isLive && !pass){

                //boss出场危险提示
                slipBoss.draw(g);
                //画boss
                boss.draw(g);
                for (Bullet bullet : bulletList) {
                    bullet.colBossChecked(boss);
                }
                for (Skill skill : skills) {
                    skill.colBossChecked(boss);
                }

            }

            /**
             * 如果boss死亡并且通关,移除boss
             */
            if (!Boss.isLive && pass) {
                boss = null;
                if (isPlay) {
                    play(9);
                    musicPlayer = null;
                    isPlay = false;
                }
            }





            //遍历敌方飞机一一画出来
            for (EnemyPlane enemyPlane : enemyPlanes) {
                enemyPlane.draw(g);
                enemyPlane.collisionChecking(plane);
            }
            plane.draw(g);
            plane.collisionChecking(items);



            //遍历子弹容器一一画出来
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
                bullet.collisionChecking(enemyPlanes);
//                bullet.colBossChecked(boss);
            }

            //遍历技能集合一一画出来
            for (Skill skill : skills) {
                skill.draw(g);
                skill.collisionChecking(enemyPlanes);
//                skill.colBossChecked(boss);
            }



            //画爆炸
            for (Explode explode : explodes) {
                explode.draw(g);
            }

            for (Item item : items) {
                item.draw(g);
            }
            for (ItemEffective effective : itemEfftive) {
                effective.draw(g);
            }
            for (LvUp lvUp : lvUps) {
                lvUp.draw(g);
            }

//            for (Slip slip : slips) {
//                slip.draw(g);
//            }
            //通关效果
            for (Slip slip : slips) {
                slip.draw(g);
            }
            //画分数
//            slipScore.draw(g);
            number.draw(g);

        } else {

//            if (Plane.blood >= 0) {
//                plane.draw(g);
//                //画分数
//                slipScore.draw(g);
//            }
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

    /**
     * 添加音效方法
     * @param type 音效类型
     */
    private void play(int type){
        musicPlayer = new MusicPlayer(type);
        musicPlayer.start();
    }

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
        DataStore.put("boss",boss);
        Start start = new Start(0);
        starts.add(start);
        slips.add(slipScore);

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

        /**
         * 添加鼠标监听器
         */
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                for (Start start1 : starts) {
//                    start1.mousePressed(e);
//                }
//
//            }
//        });

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
