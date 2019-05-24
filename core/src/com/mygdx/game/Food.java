package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Food {

    private int x;
    private int y;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    //use LibGDX's own MathUtils random method
    public void randomisePos(int boardSize) {
        x = MathUtils.random(boardSize-1);
        y = MathUtils.random(boardSize-1);
    }

    //to create the food and randomise its position
    public Food(int boardSize) {
        randomisePos(boardSize);
    }
}
