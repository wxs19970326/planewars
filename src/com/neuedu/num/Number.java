package com.neuedu.num;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.runtime.Boss;
import com.neuedu.runtime.Plane;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;
import com.neuedu.util.ImageUtil;

import java.awt.*;

public class Number extends BaseSprite implements Drawable {

    private static Image[] images = {
        ImageMap.get("00"),
        ImageMap.get("01"),
        ImageMap.get("02"),
        ImageMap.get("03"),
        ImageMap.get("04"),
        ImageMap.get("05"),
        ImageMap.get("06"),
        ImageMap.get("07"),
        ImageMap.get("08"),
        ImageMap.get("09")
    };

    private int ge;
    private int shi;
    private int bai;
    private int qian;

    int score;

    public Number() {
    }

    public Number(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        Plane plane = DataStore.get("Plane");
        score = plane.getScore();
        Boss boss = DataStore.get("boss");
        ge = score % 10;
        shi = score / 10 % 10;
        bai = score / 100 % 10;
        qian = score / 1000;
        g.drawImage(images[qian],FrameConstant.FRAME_WIDTH - 150, 77, images[qian].getWidth(null),
                images[qian].getHeight(null),null);
        g.drawImage(images[bai],FrameConstant.FRAME_WIDTH - 116 + 5 - 10, 77, images[bai].getWidth(null),
                images[bai].getHeight(null),null);
        g.drawImage(images[shi],FrameConstant.FRAME_WIDTH - 92 + 10 - 10, 77, images[shi].getWidth(null),
                images[shi].getHeight(null),null);
        g.drawImage(images[ge],FrameConstant.FRAME_WIDTH - 68 + 15 - 10, 77, images[ge].getWidth(null),
                images[ge].getHeight(null),null);

    }
}
