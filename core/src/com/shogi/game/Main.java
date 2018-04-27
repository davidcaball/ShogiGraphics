package com.shogi.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Created by David on 4/27/2018.
 * This class handles the switching between screens
 */

public class Main extends ApplicationAdapter{

    SpriteBatch batch;
    Texture boardTexture;
    Texture masterTexture;
    Screen game;
    Screen menu;

    //array to handle switching between screens
    ArrayList<Screen> screens= new ArrayList<Screen>();

    @Override
    public void create () {

        Constants.init();
        batch = new SpriteBatch();
        boardTexture = new Texture("extendedshogineonboard.png");
        masterTexture = new Texture("spritesheet.png");

        game = new Game(masterTexture, boardTexture);
        menu = new Menu();

        screens.add(menu);
        screens.add(game);

    }

    @Override
    public void render () {
        int nextScreen = 1;
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        nextScreen = screens.get(nextScreen).Run(batch);


        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        boardTexture.dispose();
        masterTexture.dispose();
    }
}
