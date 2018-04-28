package com.shogi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Color;


/**
 * Created by David on 4/27/2018.
 *
 */
public class Menu extends Screen{

    BitmapFont font;
    BitmapFont font2;
    Texture menuTexture;
    Rectangle playRect;
    Rectangle exitRect;
    Color unselectedColor;


    Menu(Texture menuTexture){

        this.menuTexture = menuTexture;

        unselectedColor = new Color(8f/255f, 200f/255f, 165f/255f, 255/255f);

        //creates font from .ttf file in assets folder
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("menutext.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 200;
        parameter.characters = ("PlayExit");
        parameter.shadowColor = Color.BLACK;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(unselectedColor);
        font2 = generator.generateFont(parameter);
        font2.setColor(unselectedColor);
        generator.dispose();


        //the area of the play button, used to see if play is being hovered over / selected
        playRect = new Rectangle(680, 460, 275,185);
        exitRect = new Rectangle(685, 220, 275,145);



    }
    @Override
    public int Run(SpriteBatch batch){

        //check if mouse is over the play text, if clicked while over it then switch to game screen
        if(playRect.contains(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY())) {
            font.setColor(Color.WHITE);
            if (Gdx.input.isTouched(0))
                return 1;

        }
        else
            font.setColor(unselectedColor);

        //check if mouse is over the exit text, if clicked while over it then exit the application
        if(exitRect.contains(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY())) {
            font2.setColor(Color.WHITE);
            if (Gdx.input.isTouched(0))
              Gdx.app.exit();
        }
        else
            font2.setColor(unselectedColor);




        Render(batch);
        return 0;
    }

    @Override
    public void Render(SpriteBatch batch){


        batch.draw(menuTexture, 0, 0);
        font.draw(batch, "Play", 674, 660);
        font2.draw(batch, "Exit", 700, 400);


    }


}
