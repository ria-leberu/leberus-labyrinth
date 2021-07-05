package com.example.leberuslabyrinth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class SplashScreen extends View{

    private Bitmap titleG;

    public SplashScreen(Context context){
        super(context);
        titleG = BitmapFactory.decodeResource(getResources(), R.drawable.title_leberus_labyrinth);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(titleG, 100, 100, null);
    }

}
