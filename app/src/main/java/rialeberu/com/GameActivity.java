package rialeberu.com;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity {


    private LLView gameView;
    //Play button sends us here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        //load the resolution onto a point object
        Point size = new Point();
        display.getSize(size);



        gameView = new LLView(this, size.x, size.y);
        setContentView(gameView);
    }

    //If activity is paused, pause the thread
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    //if activity is resumed, resume the thread
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }


}