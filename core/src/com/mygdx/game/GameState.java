package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameState {

    private int boardSize = 30;//How many squares in the board
    //to create board
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private int yOffset = 40;//How high the board is off the bottom


    //update game logic
    public void update(float delta){

    }


    //draw snake and board
    public void draw(int width, int height, OrthographicCamera camera) {

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(0, yOffset, width, width);

        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0+5, yOffset+5, width-5*2, width-5*2);
        //rectangle drawing happens here
        shapeRenderer.end();
    }
}
