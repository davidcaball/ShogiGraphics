package com.shogi.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;


/**
 * Created by David on 4/27/2018.
 */
public class Piece {
    private String pieceName;
    private int position;
    int id;
    Texture masterTexture;
    TextureRegion region;
    boolean promoted;
    //white for true, black for false
    boolean white = true;
    //maps a piece name to its texture coordinates




    Sprite sprite;

    //Constructor
    Piece(String pieceName, Texture masterTexture, int id, int position){
        this.pieceName = pieceName;
        this.masterTexture = masterTexture;
        this.id = id;
        this.position = position;

        //promoted initially starts at false
        promoted = false;


        region = pieceIdToTextureRegion();
        sprite = new Sprite(region);
        Vector2 coords = Constants.posToCoordinates[position];
        sprite.setPosition(coords.x, coords.y);
        sprite.setColor(Color.WHITE);

        //change the white boolean to false because a piece id above 14 is black, also rotate the piece
        //since it should be displayed upside down
        if(id < 15) {
            sprite.setColor(Color.BLUE);
            white = true;
            sprite.setRotation(180.0f);
        }

    }

    //Setters
    public void setPieceName(String pieceName){
        this.pieceName = pieceName;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPosition(int position){
        if(position >= 0 && position < 96)
            this.position = position;
        else
            System.out.println(position + " is an invalid position");
    }

    //Getters
    public String getPieceName() {
        return pieceName;
    }
    public int getPosition() {
        return position;
    }

    boolean flag = true;
    //draws this specific piece
    public void draw(SpriteBatch batch){
        if(flag) {
            System.out.println("drawing piece " + id + "pos(" + position + ") at " + sprite.getX() + ", " + sprite.getY() + "" + "is master Tex null: " + (masterTexture == null));
            flag = false;
        }
      //  + "sprite coords are " + region.getRegionX() + ", " + region.getRegionY());
        sprite.draw(batch);
    }




    //this method creates the texture region that the sprite will use, it uses a master texture passed from the
    //Game class and retrieves the coordinates for each specific piece by putting the piece id into a hashmap
    //and received a 2d vector back containing the coordinates, it then creates the region with the size specified
    //in the constants file
    public TextureRegion pieceIdToTextureRegion(){
        Vector2 coords;
        coords = Constants.spriteRegions.get(id);
      //  System.out.println("id " + id + " returns " + coords.x + " " + coords.y);
        //System.out.println((masterTexture == null) + ", " + coords.x + ", " + coords.y);
        TextureRegion texRegion = new TextureRegion(masterTexture, (int)coords.x, (int)coords.y, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE);
        return texRegion;
    }

    public void updateTexture(int id){
        Vector2 coords;
        coords = Constants.spriteRegions.get(id);
        sprite.setRegion((int)coords.x, (int)coords.y, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE);
        if(id < 15) {
            sprite.setColor(Color.BLUE);
            white = true;
            sprite.setRotation(180f);
        }
        else{
            sprite.setColor(Color.WHITE);
            white = false;
            sprite.setRotation(0f);
        }
    }









}
