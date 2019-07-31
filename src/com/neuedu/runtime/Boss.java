package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Boss extends BaseSprite implements Drawable, Moveable {

    private int speed = FrameConstant.SPEED * 2;
    private boolean dir = false;
    private int timer;
    private Image[] image = {
            ImageMap.get("boss1"),
            ImageMap.get("boss2"),
            ImageMap.get("boss3"),
            ImageMap.get("boss4"),
            ImageMap.get("boss5"),
            ImageMap.get("boss6"),
            ImageMap.get("boss7"),
            ImageMap.get("boss8"),
            ImageMap.get("boss9"),
    };
    public static boolean isLive = true;
    private int blood = image[0].getWidth(null);
    public static boolean isBoold;
    private int count;

    public Boss() {
        this(
                FrameConstant.FRAME_WIDTH / 2 - ImageMap.get("boss1").getWidth(null) / 2,
                -35 - ImageMap.get("boss1").getHeight(null)
        );
    }

    public Boss(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        move();
        setBoold();
        timer++;
        g.drawImage(image[count],getX(), getY(), image[count].getWidth(null),
                image[count].getHeight(null),null);
        g.setColor(Color.green);
        g.drawRoundRect(getX(),getY() + 5,image[count].getWidth(null),10,5,5);
        g.fillRoundRect(getX(),getY() + 5,blood,10,5,5);
        if (timer == 2) {
            count++;
            if (count == 8) {
                count = 0;
            }
            timer = 0;
        }
    }

    @Override
    public void move() {
        if (isLive) {
            if (getY() <= 40) {
                setY(getY() + speed);
            }else {
                if (!dir) {
                    setX(getX() - speed);
                    if (getX() <= 0) {
                        dir = true;
                    }
                } else {
                    setX(getX() + speed);
                    if (getX() >= FrameConstant.FRAME_WIDTH - image[0].getWidth(null)){
                        dir = false;
                    }
                }
            }
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image[0].getWidth(null), image[0].getHeight(null));
    }

    /**
     *boss开枪
     */

    private void setBoold(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (isBoold) {
            blood = blood - 2;
            isBoold = false;
            if (blood <= 0) {
                isLive = false;
                Explode explode = new Explode(getX(),getY(),9);
                gameFrame.explodes.add(explode);
                GameFrame.pass = true;
            }
        }
    }


}
