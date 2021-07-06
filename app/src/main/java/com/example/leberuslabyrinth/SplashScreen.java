package com.example.leberuslabyrinth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.MotionEvent;

public class SplashScreen extends View{

    //title graphic setup
    private Bitmap titleG;
    private int scrW; private int scrH;

    //play button setup
    private Bitmap playBtnUp;
    private Bitmap playBtnDn;
    private boolean playBtnPressed;

    public SplashScreen(Context context){
        super(context);
        titleG = BitmapFactory.decodeResource(getResources(), R.drawable.title_leberus_labyrinth);

        playBtnUp = BitmapFactory.decodeResource(getResources(),R.drawable.playbutton_up_leberulabyrith);
        playBtnDn = BitmapFactory.decodeResource(getResources(),R.drawable.playbutton_down_leberulabyrith);

    }
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        scrW = w; scrH = h;
    }

    public boolean onTouchEven(MotionEvent event) {
        int evtAction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();

        switch (evtAction) {
            case MotionEvent.ACTION_DOWN:
                int btnLeft = (scrW - playBtnUp.getWidth())/2;
                int btnRight = btnLeft + playBtnUp.getWidth();
                int btnTop = (int) (scrH*0.5);
                int btnBottom = btnTop + playBtnUp.getHeight();

                boolean withinBtnBounds = X > btnLeft && X < btnRight && Y > btnTop && Y < btnBottom;
                if(withinBtnBounds){
                    playBtnPressed = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if(playBtnPressed) {
                    //launch main game screen
                }
                playBtnPressed = false;
                break;
        }
        invalidate();
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int titleGLeftPos = (scrW - titleG.getWidth())/2;
        canvas.drawBitmap(titleG, titleGLeftPos, 100, null);

        int playBtnLeftPos = (scrW -playBtnUp.getWidth())/2;
        if (playBtnPressed) {
            canvas.drawBitmap(playBtnDn, playBtnLeftPos, (int)(scrH * 0.5), null);
        }
        else {
            canvas.drawBitmap(playBtnUp, playBtnLeftPos, (int)(scrH * 0.5), null);
        }
    }

}
