package sih.practice.teststs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class otp extends AppCompatActivity {
Button b;
    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        /*prefManager = new PrefManager(this);
        if(!prefManager.isFirstTimeLaunchOTP())
        {
            launchHomeScreen();
            finish();
        }*/
        b = (Button)findViewById(R.id.btn1);
       final Chronometer simpleChronometer =
                (Chronometer)findViewById(R.id.Chronometer01);
        long base = simpleChronometer.getBase();
        simpleChronometer.setBase(10);
        simpleChronometer.start();
        simpleChronometer.setFormat("Time Running - %s");

        simpleChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override

            public void onChronometerTick(Chronometer chronometer)
            {

            }

        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
    /*private void launchHomeScreen()
    {
        prefManager.setFirstTimeLaunchOTP(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }*/
}
