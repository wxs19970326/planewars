package com.neuedu.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;

public class MusicPlayer extends Thread {

    private Player player;
    private static File musicMyBullet = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\mybullet.mp3");
    private static File musicEnBullet = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\SOUND_ENEMY_SHOT_01.mp3");
    private static File item = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\item.mp3");
    private static File lvup = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\SOUND_LEVELUP_01.mp3");
//    private static File warn = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\warn.mp3");
    private static File crash = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\crash.mp3");
    private static File gameOver = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\geamover.mp3");
    private static File skill = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\skill.mp3");
    private static File start = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\start.mp3");
    private static File win = new File("F:\\workspace_IDEA\\planewars\\src\\com\\neuedu\\music\\win.mp3");
    private int type;

    public MusicPlayer(int type) {
        this.type = type;
    }

    @Override
    public void run() {
        super.run();

        try {
            //我方飞机音效
            if (type == 0) {
                playBullet();
            }
            //敌方飞机音效
            if (type == 1) {
                playEnBullet();
            }
            //吃道具音效
            if (type == 2) {
                playItem();
            }
            //升级音效
            if (type == 3) {
                playLvup();
            }
//            //警告音效
//            if (type == 4) {
//                playWarn();
//            }
            //爆炸音效
            if (type == 5) {
                playCrash();
            }

            //游戏结束音效
            if (type == 6) {
                playGameover();
            }

            //我方飞机技能音效
            if (type == 7) {
                playSkill();
            }
            //开始音效
            if (type == 8) {
                playStart();
            }

            if (type == 9) {
                playWin();
            }

        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 子弹音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playBullet() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(musicMyBullet));
        player = new Player(buffer);
        player.play();
    }

    /**
     * 敌机子弹音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playEnBullet() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(musicEnBullet));
        player = new Player(buffer);
        player.play();
    }

    /**
     * 吃道具音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playItem() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(item));
        player = new Player(buffer);
        player.play();
    }

    /**
     * 升级音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playLvup() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(lvup));
        player = new Player(buffer);
        player.play();
    }

    /**
     * 警告音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
//    public void playWarn() throws FileNotFoundException, JavaLayerException {
//        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(warn));
//        player = new Player(buffer);
//        player.play();
//    }

    /**
     * 爆炸音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playCrash() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(crash));
        player = new Player(buffer);
        player.play();
    }

    /**
     * 游戏结束音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playGameover() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(gameOver));
        player = new Player(buffer);
        player.play();
    }

    /**
     * 游戏结束音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playSkill() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(skill));
        player = new Player(buffer);
        player.play();
    }

    /**
     * 游戏开始音效
     * @throws FileNotFoundException
     * @throws JavaLayerException
     */
    public void playStart() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(start));
        player = new Player(buffer);
        player.play();
    }

    public void playWin() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(win));
        player = new Player(buffer);
        player.play();
    }
}