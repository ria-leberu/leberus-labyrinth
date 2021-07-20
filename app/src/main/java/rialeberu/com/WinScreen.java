package rialeberu.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WinScreen {
    private Bitmap bitmap;
    private int x, y;

    public WinScreen(Context context, int screenX, int screenY){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.screen_win);
        x = 0;
        y = 0;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
