package com.neuedu.num;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.runtime.MusicPlayer;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Start extends BaseSprite implements Drawable {

    private static Image[] images = {
            ImageMap.get("three0"), ImageMap.get("three1"), ImageMap.get("three2"), ImageMap.get("three3"), ImageMap.get("three4"), ImageMap.get("three5"),
            ImageMap.get("two6"), ImageMap.get("two7"), ImageMap.get("two8"), ImageMap.get("two9"), ImageMap.get("two10"), ImageMap.get("two11"),
            ImageMap.get("one12"), ImageMap.get("one13"), ImageMap.get("one14"), ImageMap.get("one15"), ImageMap.get("one16"), ImageMap.get("one17"),
            ImageMap.get("start0"), ImageMap.get("start1"), ImageMap.get("start2"), ImageMap.get("start3"), ImageMap.get("start4"), ImageMap.get("start5"),
            ImageMap.get("start6")
    };

    private boolean flag = true;
    private int step;
    private int type;

    public Start(int x, int y, Image[] images) {
        super(x, y);
        this.images = images;

    }

    public Start(int type) {
        this.type = type;
    }

    public Start() {
//        this(FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("start6").getWidth(null) / 2, FrameConstant.FRAME_HEIGHT / 2 - ImageMap.get("start6").getHeight(null) / 2, ImageMap.get("start6"));
    }


    @Override
    public void draw(Graphics g) {
        if (type == 0) {
            if (step >= 300) {
                flag = false;
            }
            if (flag) {
                g.drawImage(images[step / 12], FrameConstant.FRAME_WIDTH / 2 - images[step / 12].getWidth(null) / 2,
                        FrameConstant.FRAME_HEIGHT / 2 - images[step / 12].getHeight(null) / 2, images[step / 12].getWidth(null),
                        images[step / 12].getHeight(null), null);
                step++;
            }
            if (!flag) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.starts.remove(this);
                GameFrame.gameover = false;
            }
        }

        if (type == 1) {

        }
    }

    public void mousePressed(MouseEvent e) {
//        System.out.println(e.getX());
//        if (e.getX() > getX() && e.getX() < getX() + images.getWidth(null) && e.getY() > getY() && e.getY() <getY() + images.getHeight(null)) {
//            GameFrame.gameover = false;
//            GameFrame gameFrame = DataStore.get("gameFrame");
////            System.out.println("wang");
//            gameFrame.starts.remove(this);
//        }
    }

}


