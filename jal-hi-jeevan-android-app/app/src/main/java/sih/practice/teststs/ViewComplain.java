package sih.practice.teststs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

/**
 * Created by homi on 26/3/18.
 */

public class ViewComplain extends AppCompatActivity{
    String C,U;
    TextView Cid,Cl,Cat,Desc,WB,SN;
    ImageView img;
    Button b;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    boolean supported=false;
    private StorageReference mStorageRef;
    int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint);
        Intent i = getIntent();
        C=i.getStringExtra("CID");
        U=i.getStringExtra("UID");
        Cid=(TextView)findViewById(R.id.tvComplaintID);
        Cl=(TextView)findViewById(R.id.tvLocation);
        Cat=(TextView)findViewById(R.id.tvCategory);
        Desc=(TextView)findViewById(R.id.tvDescription);
        WB=(TextView)findViewById(R.id.tvWaterBody);
        SN=(TextView)findViewById(R.id.tvSuppNo);
        img=(ImageView)findViewById(R.id.ivImage);
        b=(Button)findViewById(R.id.SupportBut);
        b.setVisibility(View.INVISIBLE);

        database=FirebaseDatabase.getInstance();
        ref=database.getReferenceFromUrl("https://trap-cecfc.firebaseio.com/ComplaintDB");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds1 : dataSnapshot.getChildren())
                {
                    if(ds1.child("ComplaintID").getValue()!=null) {
                        Log.d("Check","TRUE "+ds1.child("ComplaintID").getValue().toString());
                        Log.d("Check","TRUE1 "+C);
                        String test = ds1.child("ComplaintID").getValue().toString();
                        if (test.equals(C)) {
                            Log.d("Check","TRUE2");
                            Cid.setText(C);
                            Cl.setText(ds1.child("State").getValue().toString());
                            Cat.setText(ds1.child("Category").getValue().toString());
                            WB.setText(ds1.child("WaterBodyType").getValue().toString());
                            Desc.setText(ds1.child("Description").getValue().toString());
                            SN.setText(ds1.child("SupportCount").getValue().toString());
                            c=Integer.parseInt(ds1.child("SupportCount").getValue().toString());
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://trap-cecfc.appspot.com/complaints/"+C+"/"+C+".jpg");
        try {
            final File localFile = File.createTempFile("def", "jpg");
            mStorageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    img.setImageBitmap(bitmap);
                    //String path=localFile.getAbsolutepath();
                }
            }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e ) {
            System.out.print(e.getMessage());
        }

        ref1=database.getReferenceFromUrl("https://trap-cecfc.firebaseio.com/ComplaintSupportDB/"+C+"/");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(U).exists()) {
                    supported=true;
                    b.setVisibility(View.VISIBLE);
                    b.setClickable(false);
                    b.setBackgroundColor(Color.parseColor("#FF008000"));
                    b.setText("Supported");

                }
                else
                {
                    b.setVisibility(View.VISIBLE);
                    b.setClickable(true);
                }
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDB udb=new UserDB(getApplicationContext());
                udb.InsertComplaintSupportData(C,U);
                b.setClickable(false);
                b.setBackgroundColor(Color.parseColor("#FF008000"));
                b.setText("Supported");
                c++;
                FirebaseDatabase.getInstance().getReferenceFromUrl("https://trap-cecfc.firebaseio.com/ComplaintDB/"+C+"/SupportCount").setValue(String.valueOf(c));
                SN.setText(""+c);
            }
        });

    }
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Authentication.class);
        startActivity(i);
    }

}


