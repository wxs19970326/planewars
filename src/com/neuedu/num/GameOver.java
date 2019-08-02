package com.neuedu.num;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class GameOver extends BaseSprite implements Drawable {

    private static Image[] images = {
            ImageMap.get("over0"),
            ImageMap.get("over1"),
            ImageMap.get("over2"),
            ImageMap.get("over3"),
            ImageMap.get("over4"),
            ImageMap.get("over5"),
            ImageMap.get("over6"),
            ImageMap.get("over7"),
            ImageMap.get("over8"),
            ImageMap.get("over9"),
            ImageMap.get("over10"),
            ImageMap.get("over11"),
            ImageMap.get("over12"),
            ImageMap.get("over13")
    };

    private int overStep;
    private boolean isOver = true;

    @Override
    public void draw(Graphics g) {

        if (overStep >= 195) {
            isOver = false;
        }

        if (isOver) {
            g.drawImage(images[overStep / 15], FrameConstant.FRAME_WIDTH / 2 - images[overStep / 15].getWidth(null) / 2 + 50,
                    FrameConstant.FRAME_HEIGHT / 2 - images[overStep / 15].getHeight(null) / 2, images[overStep / 15].getWidth(null) - 100,
                    images[overStep / 15].getHeight(null), null);
            overStep++;
//            if (overStep == 195){
//                overStep = 0;
//            }
        }

        if (!isOver) {
            g.drawImage(images[13], FrameConstant.FRAME_WIDTH / 2 - images[13].getWidth(null) / 2 + 50,
                    FrameConstant.FRAME_HEIGHT / 2 - images[13].getHeight(null) / 2, images[13].getWidth(null) - 100,
                    images[13].getHeight(null), null);
        }
    }
}
