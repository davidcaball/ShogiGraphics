package com.shogi.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by David on 4/27/2018.
 * The screen class is a template for all screens that should display something i.e. The menu screen,
 * the game screen, option screen etc. When Run will handle any logic or calculations that need to be
 * done while render will only draw objects
 *
 * Run() returns the index of the next screen that shall be displayed
 */
public abstract class Screen {
    abstract public int Run(SpriteBatch batch);
    abstract public void Render(SpriteBatch batch);
}
