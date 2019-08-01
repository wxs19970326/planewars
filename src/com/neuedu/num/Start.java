package com.neuedu.num;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Start extends BaseSprite implements Drawable {

    private Image images;


    private boolean flag = true;

    public Start(int x, int y, Image images) {
        super(x, y);
        this.images = images;
    }

    public Start() {
        this(FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("start6").getWidth(null) / 2, FrameConstant.FRAME_HEIGHT / 2 - ImageMap.get("start6").getHeight(null) / 2, ImageMap.get("start6"));
    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(images, FrameConstant.FRAME_WIDTH / 2 - images.getWidth(null) / 2,
                FrameConstant.FRAME_HEIGHT / 2 - images.getHeight(null) / 2, images.getWidth(null),
                images.getHeight(null), null);
    }

    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX());
        if (e.getX() > getX() && e.getX() < getX() + images.getWidth(null) && e.getY() > getY() && e.getY() <getY() + images.getHeight(null)) {
            GameFrame.gameover = false;
            GameFrame gameFrame = DataStore.get("gameFrame");
//            System.out.println("wang");
            gameFrame.starts.remove(this);
        }
    }

}


