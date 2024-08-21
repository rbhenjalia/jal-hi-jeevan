package sih.practice.teststs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class launchActivity extends AppCompatActivity {
    Chronometer c;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        c =(Chronometer)findViewById(R.id.cm);
        //c.setVisibility(View.INVISIBLE);
        c.start();
 c.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer)
            {
                if(c.getText().toString().equalsIgnoreCase("00:02"))
                {
                    //  Toast.makeText(getApplicationContext(),"asdasd",Toast.LENGTH_SHORT).show();

                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        // Do something for lollipop and above versionss
                        //Toast.makeText(getApplicationContext(),"HIGH",Toast.LENGTH_SHORT).show();
                         Intent i = new Intent(getApplicationContext(),lang.class);
                         startActivity(i);
                         finish();
                    } else{
                        Toast.makeText(getApplicationContext(),"Low",Toast.LENGTH_SHORT).show();

                        // do something for phones running an SDK before lollipop
                       Intent i = new Intent(getApplicationContext(),Authentication.class);
                        startActivity(i);
                        finish();
                      }

                }

            }
        });

    }
}
