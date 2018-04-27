package com.shogi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture boardTexture;
	Texture masterTexture;
	Board board;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		boardTexture = new Texture("extendedshogineonboard.png");
		masterTexture = new Texture("spritesheet.png");
		Constants.init();

		board = new Board(masterTexture);


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(boardTexture, 0, 0);
		board.draw(batch);


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		boardTexture.dispose();
		masterTexture.dispose();
	}
}
