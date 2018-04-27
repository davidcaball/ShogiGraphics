package com.shogi.game;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by David on 4/27/2018.
 */
public class Board {
    Texture masterTexture;
    private byte [] position = new byte[81+14+2]; //81 board spaces, 14 counters for captured pieces, and 2 king locations
    Piece[] pieceArray = new Piece[40];

    Board(Texture masterTexture){

        this.masterTexture = masterTexture;
        //initialize board

        //initialize array to 0;
        for(int i = 0; i < 97; i++){
            position[i] = 0;
        }

        //change the positions for pieces needed
        position[0] = 1; // white l
        position[1] = 3; // white n
        position[2] = 5; // white s
        position[3] = 7; // white g
        position[4] = 8; // white k
        position[5] = 7; // white g
        position[6] = 5; // white s
        position[7] = 3; // white n
        position[8] = 1; // white l
        position[10] = 11; // white b
        position[16] = 9; // white r

        //white pawns
        for(int i = 18; i < 18 + 9; i++)
            position[i] = 13;

        position[72] = 15; //black l
        position[73] = 17; //black n
        position[74] = 19; //black s
        position[75] = 21; //black g
        position[76] = 22; //black k
        position[77] = 21; //black g
        position[78] = 19; //black s
        position[79] = 17; //black n
        position[80] = 15; //black l
        position[64] = 23; //black r
        position[70] = 25; //black b

        //black pawns
        for(int i = 54; i < 54 + 9; i++)
            position[i] = 27;


        initializePieceLocations();
    }

    public void  initializePieceLocations(){
        int pieceIndex = 0;
        for(int i = 0; i < 95; i++){
            if(position[i] == 0) continue;
            System.out.println(i);
            pieceArray[pieceIndex] = new Piece("", masterTexture, position[i], i);
            pieceArray[pieceIndex].setPosition(i);
            pieceIndex++;
        }
    }

    //draws every piece in the pieceArray
    public void draw(SpriteBatch batch){
       for(Piece piece : pieceArray){
           if(piece != null) {

               piece.draw(batch);
           }
       }
    }



}
