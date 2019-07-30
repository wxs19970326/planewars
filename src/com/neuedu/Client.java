package com.neuedu;

import com.neuedu.fram.GameFrame;
import com.neuedu.util.DataStore;


public class Client {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();

        DataStore.put("gameFrame", gameFrame);
    }

}
