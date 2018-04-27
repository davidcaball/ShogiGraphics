package com.shogi.game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by David on 4/27/2018.
 */
public class Constants {


    public static final int WINDOW_HEIGHT = 1080;
    public static final int WINDOW_WIDTH = 1700;



    public static HashMap<Integer, Vector2> spriteRegions;

    //initializes the hashmap
    public static void init(){
        spriteRegions = new HashMap<Integer, Vector2>();

        spriteRegions.put(1, new Vector2(0.0f, 0.0f));
        spriteRegions.put(8, new Vector2(100.0f, 0.0f));
        spriteRegions.put(3, new Vector2(199.0f, 0.0f));
        spriteRegions.put(5, new Vector2(298.0f, 0.0f));
        spriteRegions.put(7, new Vector2(397.0f, 0.0f));
        spriteRegions.put(11, new Vector2(496.0f, 0.0f));
        spriteRegions.put(13, new Vector2(595.0f, 0.0f));
        spriteRegions.put(9, new Vector2(694.0f, 0.0f));


        spriteRegions.put(15, new Vector2(0.0f, 0.0f));
        spriteRegions.put(22, new Vector2(100.0f, 0.0f));
        spriteRegions.put(17, new Vector2(199.0f, 0.0f));
        spriteRegions.put(19, new Vector2(298.0f, 0.0f));
        spriteRegions.put(21, new Vector2(397.0f, 0.0f));
        spriteRegions.put(25, new Vector2(496.0f, 0.0f));
        spriteRegions.put(27, new Vector2(595.0f, 0.0f));
        spriteRegions.put(23, new Vector2(694.0f, 0.0f));

    }



}
