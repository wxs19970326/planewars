package com.neuedu.fram;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Backdround;
import com.neuedu.runtime.Hero;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {

    Backdround bg = new Backdround();
    Hero hero = new Hero();

    public GameFrame(){
        init();
    }

    @Override
    public void paint(Graphics g) {
        bg.draw(g);
        hero.draw(g);
    }

    private void init(){
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
        backg.setColor(Color.BLACK);
        backg.fillRect(0, 0, FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        backg.setColor(c);
        //调用虚拟图片的paint（）方法，每50ms刷新一次呢
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }

}
