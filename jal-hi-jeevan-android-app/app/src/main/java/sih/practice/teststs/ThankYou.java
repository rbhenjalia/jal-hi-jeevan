package sih.practice.teststs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankYou extends Activity{
    String CId,Uid;
    TextView cid;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thankyou);
        Intent i=getIntent();
        CId=i.getStringExtra("CID");
        Uid=i.getStringExtra("UID");
        cid=(TextView)findViewById(R.id.tvCID);
        cid.setText(CId);
        ok=(Button)findViewById(R.id.btOK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Authentication.class);
                i.putExtra("UID",Uid);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),Authentication.class);
        i.putExtra("UID",Uid);
        startActivity(i);
        finish();
    }

}
