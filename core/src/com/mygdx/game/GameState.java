package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;

public class GameState {

    private int boardSize = 30;//How many squares in the board
    //to create board
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private int yOffset = 40;//How high the board is off the bottom

    private Queue<Bodypart> mBody = new Queue<Bodypart>(); //body for snake

    public GameState() {
        mBody.addLast(new Bodypart(15,15,boardSize)); //head
        mBody.addLast(new Bodypart(15,14,boardSize));
        mBody.addLast(new Bodypart(15,13,boardSize)); //tail
    }

    //update game logic
    public void update(float delta){

    }


    //draw snake and board
    public void draw(int width, int height, OrthographicCamera camera) {

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //rectangle drawing happens here
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(0, yOffset, width, width);

        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0+5, yOffset+5, width-5*2, width-5*2);

        shapeRenderer.setColor(1,1,1,1);

        float scaleSnake = width/boardSize;
        for (Bodypart bp : mBody) {
            shapeRenderer.rect(bp.getX()*scaleSnake, bp.getY()*scaleSnake + yOffset, scaleSnake, scaleSnake);
        }


        shapeRenderer.end();
    }
}
