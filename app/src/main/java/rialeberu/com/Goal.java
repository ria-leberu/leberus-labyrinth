package rialeberu.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Goal {

    private Bitmap bitmap;
    private int x, y;
    private Rect hitBox;

    public Goal(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.goal);
        x = screenX - 200;
        y = screenY - 200;

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
