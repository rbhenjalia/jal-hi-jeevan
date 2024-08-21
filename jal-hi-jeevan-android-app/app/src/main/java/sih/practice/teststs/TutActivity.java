package sih.practice.teststs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut);
    }
    public void nxt(View v)
    {
       Intent i=new Intent(getApplicationContext(),MainActivity.class);
       startActivity(i);
    }
}
