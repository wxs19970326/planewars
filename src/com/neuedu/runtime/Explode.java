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
            ImageMap.get("e9")
    };
    private boolean isLive = true;
    private int step;

    public Explode() {
    }

    public Explode(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {

        if (step >= images.length) {
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

}
