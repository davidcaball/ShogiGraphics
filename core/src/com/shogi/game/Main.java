package com.shogi.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;


import java.util.ArrayList;

/**
 * Created by David on 4/27/2018.
 * This class handles the switching between screens
 */

public class Main extends ApplicationAdapter {

    SpriteBatch batch;
    Texture boardTexture;
    Texture masterTexture;
    Texture menuTexture;
    Screen game;
    Screen menu;
    int nextScreen;
    OrthographicCamera camera;
    StretchViewport viewport;


    //array to handle switching between screens
    ArrayList<Screen> screens = new ArrayList<Screen>();

    @Override
    public void create() {
        //initialize the hashmap in the constants file
        Constants.init();

        //initialize textures
        batch = new SpriteBatch();
        boardTexture = new Texture("classicboard.png");
        masterTexture = new Texture("classicspritesheet.png");
        menuTexture = new Texture("menuTexture.png");

        //Initialize camera
        camera = new OrthographicCamera(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);


        viewport = new StretchViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, camera);
        viewport.apply();

        camera.update();


        //create screens that will go into the screen Array
        game = new Game(masterTexture, boardTexture, camera);
        menu = new Menu(menuTexture);

        //set the game class as the input processor
       // Gdx.input.setInputProcessor(game);




        //add screens to the arraylist
        screens.add(menu);
        screens.add(game);

        //set first screen to 0 (menu)
        nextScreen = 0;

    }

    @Override
    public void render() {
       // System.out.println(Gdx.graphics.getDeltaTime());

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        nextScreen = screens.get(nextScreen).Run(batch);


        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        boardTexture.dispose();
        masterTexture.dispose();
        menuTexture.dispose();
    }

    //resizes the viewport when the window is resized and updates the camera
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();
    }
}







