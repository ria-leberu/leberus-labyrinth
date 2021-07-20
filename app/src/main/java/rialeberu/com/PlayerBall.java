package rialeberu.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PlayerBall {

    private Bitmap bitmap;
    private int x,y;
    private int speed = 0;

    //govern movement
    private boolean left_moving;
    private boolean right_moving;
    private boolean up_moving;
    private boolean down_moving;

    //boundaries
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    //speed limit
    private final int MIN_SPEED = 0;
    private final int MAX_SPEED = 1000;
    private final int ACCELERATION = 1;


    public PlayerBall(Context context, int screenX, int screenY) {

        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);

        x = 960;
        y = 540;

        minX = 0;
        maxX = screenX - bitmap.getWidth();

        minY = 0;
        maxY = screenY - bitmap.getHeight();


    }

    public void update() {
        //is phone tilted on x axis
        if (left_moving) {
            speed += 20;
            x = x - speed;
        }
        else if (right_moving) {
            speed += 20;
            x = x + speed;
        }

        if (up_moving) {
            speed += 20;
            y = y - speed;
        }
        else if (down_moving) {
            speed += 20;
            y = y + speed;
        }


        //speed -= 1;


        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed > MIN_SPEED) {
            speed = MIN_SPEED;
        }

        //constrain ball to screen
        if (y < minY){
            y = minY;
        }
        if (y > maxY){
            y = maxY;
        }
        if (x < minX){
            x = minX;
        }
        if (x > maxX){
            x = maxX;
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void startLeftMoving() {
        left_moving = true;
    }
    public void stopLeftMoving() {
        left_moving = false;
    }

    public void startRightMoving() {
        right_moving = true;
    }
    public void stopRightMoving() {
        right_moving = false;
    }

    public void startUpMoving() {
        up_moving = true;
    }
    public void stopUpMoving() {
        up_moving = false;
    }

    public void startDownMoving() {
        down_moving = true;
    }
    public void stopDownMoving() {
        down_moving = false;
    }

    public void stopAllMoving() {
        left_moving = false;
        right_moving = false;
        up_moving = false;
        right_moving = false;
    }
}
