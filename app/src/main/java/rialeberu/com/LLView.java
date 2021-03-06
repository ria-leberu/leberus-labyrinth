package rialeberu.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.util.Log;

public class LLView extends  SurfaceView implements Runnable{

    volatile boolean playing;
    Thread gameThread = null;

    //win state
    private boolean win = false;

    //game objects
    private PlayerBall player;
    private Goal goal;
    private WinScreen winscreen;
    private Wall1 wall1;

    //drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    //accelerometer and gyro
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    //debug
    private boolean intersection_wall = false;




    public LLView(Context context, int x, int y) {
        super(context);
        //initialize drawing objects
        ourHolder = getHolder();
        paint = new Paint();
        //initialize player ball
        player = new PlayerBall(context, x, y);
        //initialize goal
        goal = new Goal(context, x, y);
        //initialize win screen
        winscreen = new WinScreen(context, x, y);
        //initialize walls
        wall1 = new Wall1(context, x,y);

        accelerometer = new Accelerometer(context);
        gyroscope = new Gyroscope(context);

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz){

                if(rx > 1.0f){
                    player.stopLeftMoving();
                    player.startRightMoving();
                }
                else if(rx < -1.0f){
                    player.stopRightMoving();
                    player.startLeftMoving();
                }

                if(ry > 1.0f) {
                    player.stopDownMoving();
                    player.startUpMoving();
                }
                else if (ry < -1.0f) {
                    player.stopUpMoving();
                    player.startDownMoving();
                }
            }
        });
    }

    private void update() {

        if(Rect.intersects(player.getHitBox(), wall1.getHitBox())){
            intersection_wall = true;
            if (player.right_moving = true) {
                //player.right_moving = false;
                player.left_moving = true;
            }
            else if (player.left_moving = true) {
                //player.left_moving = false;
                player.right_moving = true;
            }
            else if (player.up_moving = true) {
                //player.up_moving = false;
                player.down_moving = true;
            }
            else if (player.down_moving = true) {
                //player.down_moving = false;
                player.up_moving = true;
            }

        }
        else {
            intersection_wall = false;
        }



        //if ball reaches target, player wins
        if(Rect.intersects(player.getHitBox(), goal.getHitBox())){
            win = true;
        }

        player.update();
        goal.update();
        wall1.update();
    }

    private void draw() {
        if (ourHolder.getSurface().isValid()){
            //lock area of memory that will be drawing to
            canvas = ourHolder.lockCanvas();

            //rub out last frame
            canvas.drawColor(Color.argb(255,0,0,0));


            //draw goal
            canvas.drawBitmap(goal.getBitmap(), goal.getX(), goal.getY(), paint);
            //draw player
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
            //draw wall
            canvas.drawBitmap(wall1.getBitmap(), wall1.getX(), wall1.getY(), paint);


            if (win) {
                canvas.drawBitmap(winscreen.getBitmap(), winscreen.getX(), winscreen.getY(), paint);
            }

            //unlock and draw scene
            ourHolder.unlockCanvasAndPost(canvas);

            //debugging text
            printDebuggingText();

        }
    }

    private void control() {
        try{
            gameThread.sleep(17); //aim for 60FPS
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    //clean up game thread if interrupted or user quits
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {

        }
        accelerometer.unregister();
        gyroscope.unregister();
    }
    //make and start new thread
    public void resume() {
        accelerometer.register();
        gyroscope.register();
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void printDebuggingText(){
        Log.d("leftMoving", ""+player.left_moving);
        Log.d("rightMoving", ""+player.right_moving);
        Log.d("upMoving", ""+player.up_moving);
        Log.d("downMoving", ""+player.down_moving);
        Log.d("wallIntersection", ""+intersection_wall);
    }




}
