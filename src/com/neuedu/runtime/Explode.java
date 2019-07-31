package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Explode extends BaseSprite implements Drawable {

    private Image[] images = {
            ImageMap.get("e1"), ImageMap.get("e2"), ImageMap.get("e3"), ImageMap.get("e4"),
            ImageMap.get("e5"), ImageMap.get("e6"), ImageMap.get("e7"), ImageMap.get("e8"),
            ImageMap.get("e9"), ImageMap.get("1"), ImageMap.get("2"), ImageMap.get("3"), ImageMap.get("4"),
            ImageMap.get("5"), ImageMap.get("6"), ImageMap.get("7"), ImageMap.get("8"),ImageMap.get("9")
    };
    private boolean isLive = true;
    private boolean bossExplode = true;
    private int step;
    private int timer;

    private int type;

    public Explode() {
    }

    public Explode(int x, int y, int type) {
        super(x, y);
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {

        if (type == 0) {
            if (step >= 9) {
                isLive = false;
            }

            if (isLive) {
                g.drawImage(images[step],getX(), getY(), images[step].getWidth(null),
                        images[step].getHeight(null),null);
                step++;
            }

            if (!isLive) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.explodes.remove(this);
            }
        }
        if (type == 9) {
            timer++;
            if (step >= 9) {
                bossExplode = false;
            }
            System.out.println(step);

            if (bossExplode) {
                g.drawImage(images[step + type],getX(), getY(), images[step + type].getWidth(null),
                        images[step + type].getHeight(null),null);
                if (timer == 20) {
                    step++;
                    timer = 0;
                }
            }

            if (!bossExplode) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.explodes.remove(this);
            }
        }

    }

}
