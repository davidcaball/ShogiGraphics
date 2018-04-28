package com.shogi.game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by David on 4/27/2018.
 */
public class Constants {


    public static final int WINDOW_HEIGHT = 1060;
    public static final int WINDOW_WIDTH = 1700;

    public static final int SQUARE_SIZE = 100;



    public static HashMap<Integer, Vector2> spriteRegions;

    //Maps a position on the board (0-94) to the coordinates the sprite should be drawn at
    public static Vector2[] posToCoordinates = new Vector2[95];


    //initializes the hashmap
    public static void init(){
        spriteRegions = new HashMap<Integer, Vector2>();

        spriteRegions.put(1, new Vector2(0.0f, 0.0f));
        spriteRegions.put(8, new Vector2(100.0f, 0.0f));
        spriteRegions.put(3, new Vector2(199.0f, 0.0f));
        spriteRegions.put(5, new Vector2(298.0f, 0.0f));
        spriteRegions.put(7, new Vector2(397.0f, 0.0f));
        spriteRegions.put(9, new Vector2(496.0f, 0.0f));
        spriteRegions.put(13, new Vector2(595.0f, 0.0f));
        spriteRegions.put(11, new Vector2(694.0f, 0.0f));


        spriteRegions.put(15, new Vector2(0.0f, 0.0f));
        spriteRegions.put(22, new Vector2(100.0f, 0.0f));
        spriteRegions.put(17, new Vector2(199.0f, 0.0f));
        spriteRegions.put(19, new Vector2(298.0f, 0.0f));
        spriteRegions.put(21, new Vector2(397.0f, 0.0f));
        spriteRegions.put(23, new Vector2(496.0f, 0.0f));
        spriteRegions.put(27, new Vector2(595.0f, 0.0f));
        spriteRegions.put(25, new Vector2(694.0f, 0.0f));


        //Fills the first 80 elements of the array with the vector2's of their corresponding coordinates
        for(int pos = 0; pos < 81; pos++){
            Vector2 start = new Vector2(1175.0f, 870f);
            int column = pos % 9;
            int row = pos / 9;

            start.x -= 100 * column;
            start.y -= 100 * row;

            posToCoordinates[pos] = start;
        }

        //WHITE CAPTURE ZONE
        posToCoordinates[81] = new Vector2(186, WINDOW_HEIGHT - 389);
        posToCoordinates[82] = new Vector2(86, WINDOW_HEIGHT -389);
        posToCoordinates[83] = new Vector2(186, WINDOW_HEIGHT -290);
        posToCoordinates[84] = new Vector2(86, WINDOW_HEIGHT - 290);
        posToCoordinates[85] = new Vector2(238, WINDOW_HEIGHT -192);
        posToCoordinates[86] = new Vector2(138, WINDOW_HEIGHT -192);
        posToCoordinates[87] = new Vector2(38, WINDOW_HEIGHT -192);

        //BLACK CAPTURE ZONE
        posToCoordinates[88] = new Vector2(1408,WINDOW_HEIGHT - 789);
        posToCoordinates[89] = new Vector2(1508,WINDOW_HEIGHT - 789);
        posToCoordinates[90] = new Vector2(1408,WINDOW_HEIGHT - 889);
        posToCoordinates[91] = new Vector2(1508,WINDOW_HEIGHT - 889);
        posToCoordinates[92] = new Vector2(1357,WINDOW_HEIGHT - 987);
        posToCoordinates[93] = new Vector2(1457,WINDOW_HEIGHT - 987);
        posToCoordinates[94] = new Vector2(1557,WINDOW_HEIGHT - 987);
    }






}
