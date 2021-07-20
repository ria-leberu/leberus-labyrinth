package rialeberu.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{



    @Override
    public void onClick(View v) {
        //for the play button
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //set UI layout as the view
        setContentView(R.layout.activity_main);

        //find reference to button
        final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);

        //Listen for clicks
        buttonPlay.setOnClickListener(this);


    }
}