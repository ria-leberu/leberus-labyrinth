package rialeberu.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Wall1 {
    private Bitmap bitmap;
    private int x, y;
    private Rect hitBox;

    public Wall1(Context context, int screenX, int screenY) {

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall1);
        x = screenX - 700;
        y = screenY - bitmap.getHeight();

        //initialize hitbox
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

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

    public void update() {
        //refresh hitbox location
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }

    public Rect getHitBox() {
        return hitBox;
    }


}
