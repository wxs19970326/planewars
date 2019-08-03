package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class ItemEffective extends BaseSprite implements Drawable, Moveable {

    private Image[] images = {
            ImageMap.get("eff01")
    };

    Plane plane = DataStore.get("Plane");

    public ItemEffective() {
    }

    public ItemEffective(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        Plane plane = DataStore.get("Plane");
        g.drawImage(images[0], plane.getX() - 16, plane.getY() - 35, plane.getImage().getHeight(null) * 2,
                plane.getImage().getHeight(null) * 2,null);
    }

    @Override
    public void move() {

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), images[0].getWidth(null), images[0].getHeight(null));
    }
}
