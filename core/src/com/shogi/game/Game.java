package com.shogi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;

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

	Game(Texture masterTexture, Texture boardTexture, Camera camera){
			this.masterTexture = masterTexture;
		this.boardTexture = boardTexture;

		board = new Board(masterTexture, boardTexture, camera);
	}

	@Override
	public int Run(SpriteBatch batch){
		board.update();
		Render(batch);
		return 1;
	}

	public void Render(SpriteBatch batch){
		board.draw(batch);
	}
}
