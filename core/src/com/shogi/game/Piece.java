package com.shogi.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.math.Vector2;



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
    boolean white;
    //maps a piece name to its texture coordinates


    Sprite sprite;

    //Constructor
    Piece(String pieceName, Texture masterTexture, int id){
        this.pieceName = pieceName;
        promoted = false;
        this.masterTexture = masterTexture;
        this.id = id;
        sprite = new Sprite(pieceIdToTextureRegion());
        Vector2 coords = positionToCoordinates(position);
        sprite.setPosition(coords.x, coords.y);
    }

    //Setters
    public void setPieceName(String pieceName){
        this.pieceName = pieceName;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPosition(int position){
        if(position > 0 && position < 96)
            this.position = position;
        else
            System.out.println("Invalid Position");
    }

    //Getters
    public String getPieceName() {
        return pieceName;
    }
    public int getPosition() {
        return position;
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    //gets the position of a piece and returns the coordinates it should be drawn at as a Vector2
    public Vector2 positionToCoordinates(int pos){
        Vector2 start = new Vector2(1175.0f, 90.0f);
        if(pos > 80){
            //TODO: get captured locations
        }
        else{
            int column = position % 9;
            int row = position / 9;

            start.x -= 100 * column;
            start.y += 100 * row;
        }
        return start;
    }

    public TextureRegion pieceIdToTextureRegion(){
        Vector2 region;
        region = Constants.spriteRegions.get(id);
        TextureRegion texRegion= new TextureRegion(masterTexture, region.x, region.y, 100, 100);
        return texRegion;
    }


}
