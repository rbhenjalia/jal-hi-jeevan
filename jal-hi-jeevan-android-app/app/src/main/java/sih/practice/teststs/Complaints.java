package sih.practice.teststs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Complaints extends AppCompatActivity
{
    Spinner pspin, wspin;
    EditText desc;
    TextView s1,s2;
    Button next;
    int a,b;

    String u,uid;
    Double la;
    Double lo;
    Intent i;
    private int GALLERY = 1, CAMERA = 2;
    private static final String IMAGE_DIRECTORY = "/JHJ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        Intent iEx = getIntent();
        u = iEx.getStringExtra("URI");
        la = iEx.getDoubleExtra("Latitude",0);
        lo = iEx.getDoubleExtra("Longitude",0);
        uid=iEx.getStringExtra("UID");

        String en = getString(R.string.Encroachment);
        String in = getString(R.string.Industrial);
        String hw = getString(R.string.HumanWaste);
        String adb = getString(R.string.AnimalDeadBodies);
        String s = getString(R.string.Sewage);
        String p = getString(R.string.Plastic);
        String ws = getString(R.string.WaterScarcity);
        String sm = getString(R.string.SandMining);
        String ue = getString(R.string.UnsafeEmbankment);
        String g=getString(R.string.Ganga);
        String o = getString(R.string.Other);

        String r = getString(R.string.River);
        String l = getString(R.string.Lake);
        String pond = getString(R.string.Pond);
        String beach = getString(R.string.Beach);
        String canal = getString(R.string.Canal);
        String well = getString(R.string.Well);

        final String[] pcategory = { en, in, hw, adb, s, p, ws, sm,ue,g,o };
        final String[] wcategory = { r,l,pond,beach,canal,well,o };

        pspin = (Spinner) findViewById(R.id.pspinner);
        wspin = (Spinner) findViewById(R.id.wspinner);

        next = (Button) findViewById(R.id.toSuggestion);
        s1 = (TextView)findViewById(R.id.secret1);
        s2 = (TextView)findViewById(R.id.secret2);

        desc = (EditText)findViewById(R.id.etDescription);


        ArrayAdapter pa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pcategory);
        pa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pspin.setAdapter(pa);
        pspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1.setText(pcategory[position]);
                a=position;
                if(position==9)
                {
                    wspin.setSelection(0);
                    wspin.setEnabled(false);
                }
                else
                {
                    wspin.setEnabled(true);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter wa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,wcategory);
        wa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wspin.setAdapter(wa);
        wspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s2.setText(wcategory[position]);
                b=position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desc.getText().toString().length()>250)
                {
                    Toast.makeText(getApplicationContext(), "Description under 250 characters!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), s1.getText().toString() + s2.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(v.getContext(), Suggestion.class);
                    i.putExtra("Uri",u);
                    i.putExtra("Latitude",la);
                    i.putExtra("Longitude",lo);
                    i.putExtra("Problem",s1.getText().toString());
                    i.putExtra("PrNo",a);
                    i.putExtra("WaterBody",s2.getText().toString());
                    i.putExtra("WBNo",b);
                    i.putExtra("Description",desc.getText().toString());
                    i.putExtra("UID",uid);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

}
