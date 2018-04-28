package com.shogi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by David on 4/27/2018.
 * The game class contains a Board object, the game class simply creates the board and calls
 * for the board to be drawn, it will handle user interaction
 */

public class Game extends Screen implements InputProcessor{
	SpriteBatch batch;
	Texture boardTexture;
	Texture masterTexture;
	Board board;

	Game(Texture masterTexture, Texture boardTexture, Camera camera){


		this.masterTexture = masterTexture;
		this.boardTexture = boardTexture;

		board = new Board(masterTexture, boardTexture, camera);


	}

	@Override
	public int Run(SpriteBatch batch){

		if(Gdx.input.justTouched()){
			int position = board.getPositionFromMouse();

			int testid = -1;
			for(int i = 0; i < 40; i++){
				if(board.pieceArray[i].id == board.position[position]){
					testid = board.pieceArray[i].id;
				}
			}
			System.out.println("Selecting square " + position + " has position value: " + board.position[position] + " the piece has id " + testid);

			if(position >= 0){
				if(board.pieceSelected){
					board.attemptMove(position);
				}
				board.selectSquare(position);
			}

		}

		board.update();
		Render(batch);
		return 1;

	}

	public void Render(SpriteBatch batch){
		board.draw(batch);


	}






	//input methods

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int position = board.getPositionFromMouse();
		System.out.println("Selecting square " + position);

		if(position >= 0){
			board.selectSquare(position);
			System.out.println("Selecting square " + position);
		}
		return true;
	}





	@Override
	public boolean keyDown(int keycode) {
		System.out.println("A");
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}

