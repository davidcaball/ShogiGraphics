package com.shogi.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


import java.util.ArrayList;

/**
 * Created by David on 4/27/2018.
 * This class handles the switching between screens
 */

public class Main extends ApplicationAdapter{
    SpriteBatch batch;
    Texture boardTexture;
    Texture masterTexture;
    Texture menuTexture;
    Screen game;
    Screen menu;
    int nextScreen;
    OrthographicCamera camera;


    //array to handle switching between screens
    ArrayList<Screen> screens= new ArrayList<Screen>();

    @Override
    public void create () {
        //initialize the hashmap in the constants file
        Constants.init();

        //initialize textures
        batch = new SpriteBatch();
        boardTexture = new Texture("extendedshogineonboard.png");
        masterTexture = new Texture("spritesheet.png");
        menuTexture = new Texture("menuTexture.png");

        //Initialize camera
        camera = new OrthographicCamera(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        camera.position.set(camera.viewportWidth /2f, camera.viewportHeight /2f, 0);
        camera.update();


        //create screens that will go into the screen Array
        game = new Game(masterTexture, boardTexture, camera);
        menu = new Menu(menuTexture);


        //add screens to the arraylist
        screens.add(menu);
        screens.add(game);

        //set first screen to 0 (menu)
        nextScreen = 1;

    }

    @Override
    public void render () {
       System.out.println(Gdx.graphics.getDeltaTime());

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        menuTexture.dispose();
    }
}
