package sih.practice.teststs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Suggestion extends AppCompatActivity
{

int pn,wn;
String u,w,p,d,uid,path;
Double la,lo;
StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("complaints");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Intent iEx = getIntent();
        u = iEx.getStringExtra("Uri");
        la = iEx.getDoubleExtra("Latitude",0);
        lo = iEx.getDoubleExtra("Longitude",0);
        p = iEx.getStringExtra("Problem");
        w = iEx.getStringExtra("WaterBody");
        d = iEx.getStringExtra("Description");
        pn = iEx.getIntExtra("PrNo",0);
        wn = iEx.getIntExtra("WBNo",0);
        uid=iEx.getStringExtra("UID");



        final String[] pcategory = { "Encroachment","Industrial","Human Waste","Animal Dead Bodies","Sewage","Plastic","Water Scarcity","Sand Mining","Unsafe Embankment","Ganga","Other" };
        final String[] wcategory = { "River","Lake","Pond","Beach","Canal","Well","Other" };
        p=pcategory[pn];
        w=wcategory[wn];
    }
    public void Skip(View view)
    {

        EditText sugg = (EditText)findViewById(R.id.etSuggestion);
            //Toast.makeText(getApplicationContext(), "Thank you", Toast.LENGTH_LONG).show();
            final Intent i = new Intent(getApplicationContext(),ThankYou.class);

            final String res[]=getAddress(la,lo);
            Date dt = new Date();
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dateFormatter.format(dt);
            final String x = dateFormatter.format(dt);
            String PC = res[4];
            final String cid=CID(pn,PC,wn);
            //Toast.makeText(getApplicationContext(),u,Toast.LENGTH_SHORT).show();
            File f=new File(u);

            final UserDB ux = new UserDB(this);
            File F = new File(u);
            Uri uri = Uri.fromFile(F);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
            StorageReference ref = storageReference.child(cid+"/"+cid+".jpg");
            ref.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();

                        Uri downUri=taskSnapshot.getDownloadUrl();
                        path=downUri.toString();
                        ux.InsertComplaintData(cid,uid,p,d,"",res[3],res[1]+" "+res[2],"Pending",x,res[0],w,res[4],String.valueOf(la)+","+String.valueOf("lo"),"1",path);
                        ux.InsertComplaintSupportData(cid,uid);
                        i.putExtra("CID",cid);
                        i.putExtra("UID",uid);
                        startActivity(i);
                        finish();
                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    }
                });
            //Log.d("Upload",path);
            //Log.d("Upload",res[0]+"\n"+res[1]+"\n"+res[2]+"\n"+res[3]+"\n"+res[4]);


    }
    public void Submit(View view)
    {
        EditText sugg = (EditText)findViewById(R.id.etSuggestion);
        if(sugg.getText().toString().length()>250)
        {
            Toast.makeText(getApplicationContext(), "Suggestion not more than 250 characters!", Toast.LENGTH_LONG).show();
        }
        else {
            final Intent i = new Intent(getApplicationContext(),ThankYou.class);
            final String res[]=getAddress(la,lo);
            Date dt = new Date();
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dateFormatter.format(dt);
            final String x = dateFormatter.format(dt);
            String PC = res[4];
            final String cid=CID(pn,PC,wn);
            //Toast.makeText(getApplicationContext(),u,Toast.LENGTH_SHORT).show();
            File f=new File(u);
            final UserDB ux = new UserDB(this);
            File F = new File(u);
            Uri uri = Uri.fromFile(F);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final StorageReference[] ref = {storageReference.child(cid + "/" + cid + ".jpg")};
            ref[0].putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();

                            Uri downUri=taskSnapshot.getDownloadUrl();
                            String path =downUri.toString();
                            ux.InsertComplaintData(cid,uid, p,d,"",res[3],res[1]+" "+res[2],"Pending",x,res[0],w,res[4],String.valueOf(la)+","+String.valueOf(lo),"1",path);
                            ux.InsertComplaintSupportData(cid,uid);
                            i.putExtra("CID",cid);
                            i.putExtra("UID",uid);
                            startActivity(i);
                            finish();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
    //complaintID
    public String CID(int Problem,String PosCode,int WaterBody){
        Random randomNum = new Random();
        int number=randomNum.nextInt(10000);
        String f=String.valueOf(Problem)+PosCode+String.valueOf(WaterBody)+String.valueOf(number);
        return f;
    }
    //geocode
    public String[] getAddress(double lat,double lon){
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addressList = geocoder.getFromLocation(
                    lat, lon, 3);

            Address address1 = addressList.get(0);
            Address address2 = addressList.get(1);
            Address address3 = addressList.get(2);
            String Fadd = "";
            for (int i = 0; i < address1.getMaxAddressLineIndex(); i++) {
                Log.d("Upload","add1");
                Fadd += address1.getAddressLine(i);
            }
            if(Fadd.equals(""))
            {
                Log.d("Upload","add2");
                for (int i = 0; i < address2.getMaxAddressLineIndex(); i++) {
                    Fadd += address2.getAddressLine(i);
                }
            }
            if(Fadd.equals(""))
            {
                Log.d("Upload","add3");
                for (int i = 0; i < address3.getMaxAddressLineIndex(); i++) {
                    Fadd += address3.getAddressLine(i);
                }
            }

            String PostalCode = address1.getPostalCode();
            if(PostalCode==null)
            {
                PostalCode = address2.getPostalCode();
            }
            if(PostalCode==null)
            {
                PostalCode = address3.getPostalCode();
            }
            Fadd=address1.getAddressLine(0);
            String Locality = address2.getSubLocality();
            String Country = address1.getCountryName();
            String State = address1.getAdminArea();
            String City = address2.getLocality();
            String[] result = {Fadd, City, Locality, State, PostalCode, Country};
            Log.d("Upload","Lat "+lat+" lon "+lon);
            Log.d("Upload","Fadd="+Fadd+"\nCity="+City+"\nLocality="+Locality+"\nState="+State+"\nPostalCode="+PostalCode+"\nCountry="+Country);
            //Toast.makeText(getApplicationContext(),"Geocoded",Toast.LENGTH_SHORT).show();
            return result;
        }
        catch (IOException e)
        {
            Toast.makeText(this,"Unable to connect to Geocode server",Toast.LENGTH_SHORT);
        }
        return null;
    }
    private void killActivity() {
        finish();
    }
    public void onPause()
    {
        super.onPause();
        finish();
    }
    public void onStop()
    {
        super.onStop();
        finish();
    }
}
