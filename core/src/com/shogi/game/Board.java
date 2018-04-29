package com.shogi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;

/**
 * Created by David on 4/27/2018.
 */
public class Board {
    Texture masterTexture;
    Texture boardTexture;
    Sprite hoveredSquare;
    Sprite selectedSquare;
    int selectedPosition;
    Rectangle boardRectangle;
    Camera camera;
    Boolean pieceSelected;
    ArrayList<Sprite> possibleMoveSprites;

    //holds the possible moves for the selected piece
    ArrayList<Integer> moves = new ArrayList<Integer>();

    //this keeps track of how many possible moves there are for the piece that is selected, it
    //prevents drawing uncecessary sprites
    int possibleMovesForCurrentSelection;


    byte [] position = new byte[81+14+2]; //81 board spaces, 14 counters for captured pieces, and 2 king locations
    Piece[] pieceArray = new Piece[40];

    Board(Texture masterTexture, Texture boardTexture, Camera camera){

        pieceSelected = false;
        boardRectangle = new Rectangle(373, 86, 897, 899);

        hoveredSquare = new Sprite(masterTexture, 694, 100, 100, 100);

        selectedSquare = new Sprite(masterTexture, 694, 100, 100, 100);
        selectedSquare.setColor(Color.GREEN);
        selectedSquare.setAlpha(1f);

        //assign arguments to classes member variables
        this.masterTexture = masterTexture;
        this.boardTexture = boardTexture;
        this.camera = camera;


        //initialize board
        initializePositionArray();

        initializePieceLocations();


        possibleMoveSprites = new ArrayList<Sprite>();

        //initialize array that highlights cells showing possible moves
        for(int i = 0; i < 18; i++){
            possibleMoveSprites.add(new Sprite(masterTexture, 694, 100, 100, 100));
            possibleMoveSprites.get(i).setColor(Color.BLUE);
        }



    }





    /*
   ***********************UPDATE AND DRAW METHODS***********************
    */




    //handles all the logic that happens while the board is open, then calls the
    public void update(){



        // System.out.println(getPositionFromMouse());

        //372, 86
        int x = Gdx.input.getX();
        int y = Constants.WINDOW_HEIGHT - Gdx.input.getY();

        if(pieceSelected){
            selectedSquare.setAlpha(1f);
        }
        else{
            selectedSquare.setAlpha(0f);
        }

        //takes user input and highlights the selected square
       highlightHoveredSquare();

    }




    //draws every piece in the pieceArray
    public void draw(SpriteBatch batch){

        batch.draw(boardTexture, 0, 0);
       for(Piece piece : pieceArray){
           if(piece != null) {

               piece.draw(batch);
           }
       }

        selectedSquare.draw(batch);
        hoveredSquare.draw(batch);
        for(int i = 0; i < possibleMovesForCurrentSelection && i < possibleMoveSprites.size(); i++){
            possibleMoveSprites.get(i).draw(batch);
        }

    }






     /*
   ***********************SECONDARY METHODS***********************
    */


    //checks to see if piece at one position can capture another, if it can it will call the capture
    //method, if not it will print out an error message
    public void attemptCapture(int currentPos, int posToCapture){
        if(position[currentPos] == 0){
            System.out.println("Position is empty, can not capture from this position");
            return;
        }
        if(position[currentPos] < 15){
            if(position[posToCapture] > 14){
                capture(currentPos, posToCapture);
            }
            else{
                System.out.println("Can no capture your own piece");
            }
        }
        else if(position[currentPos] > 14){
            if(position[posToCapture] < 15){
                capture(currentPos, posToCapture);
            }
            else{
                System.out.println("Can no capture your own piece");
            }
        }
    }



    //performs the capture by moving the selected piece to the position to capture, it will then
    //move the captured piece to the capture zone
    public void capture(int currentPos, int posToCapture){

    }

    //this method loops through the position array and updates the positon of the pieces to reflect
    //new data, should be called after making a move, capturing a piece, dropping a piece etc.
    public void updateLocations(){
        int pieceIndex = 0;
        for(int i = 0; i < 95; i++){


            System.out.println("POSITION ARRAY: pos: " + i + " = " + position[i]);

            if(position[i] == 0) continue;


            pieceArray[pieceIndex].setId(position[i]);
            pieceArray[pieceIndex].setPosition(i);
            Vector2 newCoords = Constants.posToCoordinates[i];
            pieceArray[pieceIndex].sprite.setPosition(newCoords.x, newCoords.y);
            pieceArray[pieceIndex].updateTexture(position[i]);

            System.out.println("PIECE ARRAY: pos: " + pieceArray[pieceIndex].getPosition() + " = " + pieceArray[pieceIndex].id);
            pieceIndex++;
        }
    }


    //attempts to make a move by checking if it is in the set of legal moves, if it is, it will
    //call makeMove and return true back to the game class so it can switch the players turn
    public Boolean attemptMove(int move){
        for(int i = 0; i < moves.size(); i++){
            if(moves.get(i) == move) {
                makeMove(move);
                return true;
            }
        }
        return false;
    }

    public void makeMove(int move){
        System.out.println("Swapping pos: " + selectedPosition + " with id " + position[selectedPosition] + " with pos: " + move + " with id " + position[move]);
        position[move] = position[selectedPosition];
        position[selectedPosition] = 0;
        System.out.println("Moving piece at " + selectedPosition);
        pieceSelected = false;
        updateLocations();
    }

    //highlights a cell if it has been clicked and there is a piece in that square
    public void selectSquare(int pos){
        if(position[pos] == 0){
            possibleMovesForCurrentSelection = 0;
            pieceSelected = false;
            selectedPosition = -1;
            return;
        }
        Vector2 coords = getStepCoordinateFromPosition(pos);
        selectedSquare.setPosition(coords.x, coords.y);
        pieceSelected = true;
        selectedPosition = pos;
        showPossibleMoves(pos);
    }

    public void showPossibleMoves(int pos){
        moves = getPossibleMoves(pos, position[pos]);
        possibleMovesForCurrentSelection = moves.size();
        int i;
        for(i = 0; i < moves.size() && i < possibleMoveSprites.size(); i++){
            Vector2 coords= getStepCoordinateFromPosition(moves.get(i));
            possibleMoveSprites.get(i).setPosition(coords.x, coords.y);
            possibleMoveSprites.get(i).setAlpha(1.0f);
        }
        for(; i < possibleMoveSprites.size(); i++){
            possibleMoveSprites.get(i).setAlpha(0f);
        }
    }



    public int getPositionFromMouse(){
        Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(input);
        System.out.print("X: " + input.x + "Y: " + input.y);
        int row = 0, column = 0;
        if(boardRectangle.contains(input.x, input.y)) {
            input.x -= 372;
            input.y -= 86;

            column = (int)input.x / (int) 100;
            row = (int) input.y / (int) 100;
            return 80 - (row * 9 + column);
        }
        else{
            //TODO: work on captured cells
        }

       return -1;

    }


    public void highlightHoveredSquare(){
        //code to highlight selected square
        Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(input);

        if(boardRectangle.contains(input.x,input.y)) {
            hoveredSquare.setAlpha(1.0f);

            Vector2 drawLocation = getStepCoordinateFromMouse((int)input.x, (int)input.y);


            hoveredSquare.setPosition(drawLocation.x, drawLocation.y);
        }
        else{
            hoveredSquare.setAlpha(0.0f);
        }
    }


    //returns the coordinates where a square should be drawn to highlight a cell
    //this is the equivalent to the top right corner of the cell the coordinates
    //x and y are in
    public Vector2 getStepCoordinateFromMouse(int x, int y){

        x -= 372;
        y -= 86;

        x = (int)x / (int)100 * 100 + 373;
        y = (int)y / (int)100 * 100 + 70;

        return new Vector2(x,y);
    }

    //this method takes a position (0-80) and up to 95 once it is fully implemented
    //it will return the coordinate of the top right corner of that square, which will
    //be used to highlight a cell
    public Vector2 getStepCoordinateFromPosition(int pos){

        int x = 373;
        int y = 71;
        pos = 80 - pos;
        int columns = pos % 9;
        int rows =  pos / 9;

        x += columns  * 100;
        y += rows * 100;


        return new Vector2(x,y);
    }


    //gets all the possible moves from a position
    public ArrayList<Integer> getPossibleMoves(int pos, int id){

        ArrayList<Integer> moves = new ArrayList<Integer>();

        //white pawn
        if(id == 13)
            addIfLegal(moves, pos + 9, pos);
            //black pawn
        else if(id == 27)
            addIfLegal(moves, pos - 9, pos);

            //rook
        else if(id == 9 || id == 23){
            //adds all positions in that row
            for(int i = pos - pos % 9; i < ((pos + 9) - (pos % 9)); i++) {
                if(i == pos) continue;
                moves.add(i);
            }
            //adds all moves in that column
            for(int i = pos % 9; i < 81; i+=9) {
                if(i == pos) continue;
                moves.add(i);
            }
        }

        //bishop
        else if(id == 11 || id == 25){
            for(int i = pos - 8; isInBound(i); i -= 8) { // Up + Left
                if(!addIfLegal(moves, i, pos)) break;
                if(isAtBorder(i)) break;
            }
            for(int i = pos - 10; isInBound(i); i -= 10) { //Up + Right
                if(!addIfLegal(moves, i, pos)) break;
                if(isAtBorder(i)) break;
            }
            for(int i = pos + 8; isInBound(i); i += 8){ //Down + Left
                if(!addIfLegal(moves, i, pos)) break;
                if(isAtBorder(i)) break;
            }
            for(int i = pos + 10; isInBound(i); i += 10) { //Down + Right
                if(!addIfLegal(moves, i, pos)) break;
                if(isAtBorder(i)) break;
            }
        }

        //white lance
        else if(id == 1)
            for(int i = pos + 9; i < 81; i+=9){
                addIfLegal(moves, i, pos);
            }

            //black lance
        else if(id == 15)
            for(int i = pos - 9; i >=0 ; i-=9)
                addIfLegal(moves, i, pos);

            //white knight
        else if(id == 3){
            addIfLegal(moves, pos - 18 + 1, pos);
            addIfLegal(moves, pos - 18 + 1, pos);
        }

        //white silver
        else if(id == 5){
            addIfLegal(moves, pos + 8, pos);
            addIfLegal(moves, pos + 9, pos);
            addIfLegal(moves, pos - 10, pos);
            addIfLegal(moves, pos - 8, pos);
            addIfLegal(moves, pos + 10, pos);
        }

        //black silver
        else if(id == 19){
            addIfLegal(moves, pos - 8, pos);
            addIfLegal(moves, pos - 9, pos);
            addIfLegal(moves, pos - 10, pos);
            addIfLegal(moves, pos + 8, pos);
            addIfLegal(moves, pos + 10, pos);
        }

        //white gold
        else if(id == 7){

            addIfLegal(moves, pos + 1, pos);
            addIfLegal(moves, pos - 1, pos);
            addIfLegal(moves, pos + 8, pos);
            addIfLegal(moves, pos + 9, pos);
            addIfLegal(moves, pos - 9, pos);
            addIfLegal(moves, pos + 10, pos);


        }

        //black gold
        else if(id == 21){
            addIfLegal(moves, pos - 8, pos);
            addIfLegal(moves, pos - 9, pos);
            addIfLegal(moves, pos - 10, pos);
            addIfLegal(moves, pos + 1, pos);
            addIfLegal(moves, pos - 1, pos);
            addIfLegal(moves, pos + 9, pos);
        }

        //king
        else if(id == 8 || id == 22){
            addIfLegal(moves, pos - 8, pos);
            addIfLegal(moves, pos - 9, pos);
            addIfLegal(moves, pos - 10, pos);
            addIfLegal(moves, pos + 8, pos);
            addIfLegal(moves, pos + 10, pos);
            addIfLegal(moves, pos + 1, pos);
            addIfLegal(moves, pos - 1, pos);
            addIfLegal(moves, pos + 9, pos);
        }

        //TODO: add moves for promoted piece

        return moves;
    }

    //adds the possible move to the arraylist if it is in the bounds of the board
    public boolean  addIfLegal(ArrayList<Integer> list, int move, int pos){

        if(move >= 0 && move < 81){
            Boolean isWhite = (position[pos] < 15 && position[pos] > 0);
            if(isWhite && position[move] < 15  && position[move] > 0) return false;
            if(!isWhite && position[move] > 15) return false;
            list.add(move);
            return true;
        }
        return false;
    }

    public boolean isInBound(int pos){
        if(pos >= 0 && pos < 81){
            return true;
        }
        return false;
    }

    //since we are using a 1d array it is hard to keep the bishop from rolling over to the other
    //side once it reaches an edge, this while return true is pos is on the border of the board
    public boolean isAtBorder(int pos){
        System.out.println("pos is " + pos);
        if(pos < 9 || pos > 71 || pos % 9 == 0 || pos % 9 == 8)
            return true;
        return false;

    }






     /*
   ***********************INITIALIZATION METHODS***********************
    */

    public void initializePositionArray(){
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



}
