package com.shogi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by David on 4/27/2018.
 * The game class contains a Board object, the game class simply creates the board and calls
 * for the board to be drawn, it will handle user interaction
 */

public class Game extends Screen {
	SpriteBatch batch;
	Texture boardTexture;
	Texture masterTexture;
	Board board;

	Game(Texture masterTexture, Texture boardTexture){
		this.masterTexture = masterTexture;
		this.boardTexture = boardTexture;

		board = new Board(masterTexture, boardTexture);
	}

	@Override
	public int Run(SpriteBatch batch){
		Render(batch);
		return 1;
	}

	public void Render(SpriteBatch batch){
		board.draw(batch);
	}
}
