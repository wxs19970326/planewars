package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

/**
 * 各种血条蓝条得分
 */

public class Slip extends BaseSprite implements Drawable {

    private int type;

    private static Image[] images = {
            ImageMap.get("bossblood"),
            ImageMap.get("bossblood1"),
//            ImageMap.get("planemp"),
//            ImageMap.get("planemp1")
    };

    private static Image[] imageWarning = {
            ImageMap.get("warning1"),
            ImageMap.get("warning2")
    };
    private static Image[] imageScoer = {
            ImageMap.get("score0"),
            ImageMap.get("score1"),
            ImageMap.get("score2")
    };

    private static Image[] imageWin = {
            ImageMap.get("win1"),
            ImageMap.get("win2"),
            ImageMap.get("win4"),
            ImageMap.get("win5"),
            ImageMap.get("win6"),
            ImageMap.get("win7"),
            ImageMap.get("win8"),
            ImageMap.get("win9"),
            ImageMap.get("win10"),
            ImageMap.get("win11"),
            ImageMap.get("win12"),
            ImageMap.get("win13"),
            ImageMap.get("win14"),
            ImageMap.get("win15")
    };

    //警告图计步器
    private int step;
    //警告图计时器
    private int timer;

    private boolean flag = true;

    //分数计步器
    private int scoreStep;

    //win
    private int winStep;
    private boolean winFlag = true;
    private int winTimer;


    public Slip(int type) {
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        Boss boss = DataStore.get("boss");
        //type=0和type=1,画boss血条
        if (type == 0) {
            g.drawImage(images[type],10, 40, FrameConstant.FRAME_WIDTH - 20,
                    32,null);
        }
        if (type == 1) {
            g.drawImage(images[type],15, 40, boss.getBlood(),
                    30,null);
        }
//        if (type == 2) {
//            g.drawImage(images[type],50, 110,100,13,null);
//        }

        //type=3boss出场警告
        if (type == 3) {
            if (flag) {
                g.drawImage(imageWarning[step / 20], FrameConstant.FRAME_WIDTH / 2 - imageWarning[step / 20].getWidth(null) / 2,
                        FrameConstant.FRAME_HEIGHT / 2 - imageWarning[step / 20].getHeight(null) / 2, imageWarning[step / 20].getWidth(null),
                        imageWarning[step / 20].getHeight(null), null);
                step++;
                if (step == 40) {
                    step = 0;
                }
                timer++;
            }
            if (timer == 160) {
                flag = false;
            }
        }

        //分数标志
        if (type == 4) {
            g.drawImage(imageScoer[scoreStep / 12],15, 120, imageScoer[scoreStep / 12].getWidth(null),
                    imageScoer[scoreStep / 12].getHeight(null),null);
            scoreStep++;
            if (scoreStep == 36) {
                scoreStep = 0;
            }
        }

        //胜利动画
        if (type == 5) {
            if (winFlag) {
                g.drawImage(imageWin[winStep / 15], FrameConstant.FRAME_WIDTH / 2 - imageWin[winStep / 15].getWidth(null) / 2,
                        FrameConstant.FRAME_HEIGHT / 2 - imageWin[step / 15].getHeight(null) / 2, imageWin[winStep / 15].getWidth(null),
                        imageWin[winStep / 15].getHeight(null), null);
                winStep++;
                if (winStep == 195) {
                    winStep = 0;
                }
                winTimer++;
            }
            if (winTimer == 195) {
                winFlag = false;
            }

            if (!winFlag) {
//                GameFrame gameFrame = DataStore.get("gameFrame");
//                gameFrame.slips.remove(this);
                g.drawImage(imageWin[13], FrameConstant.FRAME_WIDTH / 2 - imageWin[13].getWidth(null) / 2,
                        FrameConstant.FRAME_HEIGHT / 2 - imageWin[13].getHeight(null) / 2, imageWin[13].getWidth(null),
                        imageWin[13].getHeight(null), null);
            }

        }

    }
}
