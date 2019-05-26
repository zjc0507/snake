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

    private float mTimer = 0;//for timer

    private Controls controls = new Controls();


    private Food mFood = new Food(boardSize);

    private int snakeLength = 3;

    public GameState() {
        mBody.addLast(new Bodypart(15,15,boardSize)); //head
        mBody.addLast(new Bodypart(15,14,boardSize));
        mBody.addLast(new Bodypart(15,13,boardSize)); //tail
    }

    //update game logic
    public void update(float delta){
        mTimer = mTimer + delta;
        if (mTimer > 0.13f) {  //to control how fast the snack move
            mTimer = 0;
            advance();
            controls.update(); //update the controls every tick
        }
    }

    private void advance() {
        //mBody.addFirst(new Bodypart(mBody.first().getX(), mBody.first().getY()+1, boardSize));
        int headX = mBody.first().getX();
        int headY = mBody.first().getY();
        switch(controls.getDirection()) {
            case 0: //up
                mBody.addFirst(new Bodypart(headX, headY+1, boardSize));
                break;
            case 1: //right
                mBody.addFirst(new Bodypart(headX+1, headY, boardSize));
                break;
            case 2: //down
                mBody.addFirst(new Bodypart(headX, headY-1, boardSize));
                break;
            case 3: //left
                mBody.addFirst(new Bodypart(headX-1, headY, boardSize));
                break;
            default://should never happen
                mBody.addFirst(new Bodypart(headX, headY+1, boardSize));
                break;
        }

        if (mBody.first().getX() == mFood.getX() && mBody.first().getY() == mFood.getY()) {//check if get the food
            snakeLength++;
            mFood.randomisePos(boardSize);
        }

        for (int i = 1; i<mBody.size; i++) {     //Death
            if (mBody.get(i).getX() == mBody.first().getX()
                    && mBody.get(i).getY() == mBody.first().getY()) {
                snakeLength = 3;
            }
        }

        while (mBody.size - 1 >= snakeLength) {   //Death  >=
            mBody.removeLast();
        }

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

        shapeRenderer.setColor(1,1,1,1);//color for the snake


        float scaleSnake = width/boardSize;

        shapeRenderer.rect(mFood.getX() * scaleSnake, mFood.getY()*scaleSnake + yOffset, scaleSnake, scaleSnake);//draw a food

        for (Bodypart bp : mBody) {
            shapeRenderer.rect(bp.getX()*scaleSnake, bp.getY()*scaleSnake + yOffset, scaleSnake, scaleSnake);//draw a snake
        }



        shapeRenderer.end();
    }
}
