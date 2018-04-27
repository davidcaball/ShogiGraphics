package com.shogi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by David on 4/27/2018.
 */
public class Board {
    Texture masterTexture;
    Texture boardTexture;
    Sprite selectedSquare;
    Rectangle boardRectangle;
    Camera camera;

    private byte [] position = new byte[81+14+2]; //81 board spaces, 14 counters for captured pieces, and 2 king locations
    Piece[] pieceArray = new Piece[40];

    Board(Texture masterTexture, Texture boardTexture, Camera camera){

        boardRectangle = new Rectangle(373, 86, 897, 899);
        selectedSquare = new Sprite(masterTexture, 694, 100, 100, 100);

        //assign arguments to classes member variables
        this.masterTexture = masterTexture;
        this.boardTexture = boardTexture;
        this.camera = camera;


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
    //Creates the new objects for all the pieces
    public void  initializePieceLocations(){
        int pieceIndex = 0;
        for(int i = 0; i < 95; i++){
            if(position[i] == 0) continue;
            pieceArray[pieceIndex] = new Piece("", masterTexture, position[i], i);
            pieceArray[pieceIndex].setPosition(i);
            pieceIndex++;
        }
    }

    public void update(){

        //372, 86
        int x = Gdx.input.getX();
        int y = Constants.WINDOW_HEIGHT - Gdx.input.getY();

        if(boardRectangle.contains(x,y)) {

            System.out.println(x + ", " + y);

            x -= 372;
            y -= 86;

            x = x / 100 * 100 + 373;
            y = y / 100 * 100 + 70;

            selectedSquare.setPosition(x, y);
        }

    }

    //draws every piece in the pieceArray
    public void draw(SpriteBatch batch){
        batch.draw(boardTexture, 0, 0);
       for(Piece piece : pieceArray){
           if(piece != null) {

               piece.draw(batch);
           }
       }
        if(boardRectangle.contains(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY()))
          selectedSquare.draw(batch);

//        batch.end();
//        batch.begin();
//        ShapeRenderer shapeRenderer = new ShapeRenderer();
//        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(373, 70, 904, 904);
//        shapeRenderer.end();

    }



}
