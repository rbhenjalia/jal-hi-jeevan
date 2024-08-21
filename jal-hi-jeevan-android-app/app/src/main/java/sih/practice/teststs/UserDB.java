package sih.practice.teststs;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class UserDB
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context c;
    String r;
    public UserDB(Context context)
    {
        c=context;
    }
    public UserDB()
    {
    }

    public void uploadImage(Uri filePath,String iname)
    {
        if(filePath != null)
        {
            //final ProgressDialog progressDialog = new ProgressDialog(c);
            //progressDialog.setTitle("Uploading...");
            //progressDialog.show();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("complaints");

            StorageReference ref = storageReference.child(iname+"/"+iname+".jpg");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss();

                            //Uri downUri=taskSnapshot.getDownloadUrl();
                            //r=downUri.toString();
                            //Toast.makeText(c, "Uploaded "+r, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //progressDialog.dismiss();
                            Toast.makeText(c, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            //progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    void InsertData(String userid,
                    String name,
                    String phone,
                    String Email,
                    String City,
                    String count)
    {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://trap-cecfc.firebaseio.com");

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference UserDB = database.getReference("UserDB");

        DatabaseReference UserID = UserDB.child(userid);

        DatabaseReference UserId = UserID.child("UserId");
        UserId.setValue(userid);

        DatabaseReference Name = UserID.child("Name");
        Name.setValue(name);

        DatabaseReference Number = UserID.child("Phone");
        Number.setValue(phone);

        DatabaseReference eMail = UserID.child("EMail");
        eMail.setValue(Email);

        DatabaseReference city = UserID.child("City");
        city.setValue(City);

        DatabaseReference Count = UserID.child("count");
        Count.setValue(count);

    }

    void InsertComplaintSupportData(String ComplaintID,String UserID)
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://trap-cecfc.firebaseio.com");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ComplaintDB = database.getReference("ComplaintSupportDB");

        DatabaseReference ComplaintId = ComplaintDB.child(ComplaintID);

        DatabaseReference UserId = ComplaintId.child(UserID);
        UserId.setValue(UserID);

    }
    void InsertComplaintData(String complaintid,
                             String userid,
                             String category,
                             String description,
                             String solution,
                             String state,
                             String city,
                             String status,
                             String timestamp,
                             String address,
                             String waterbodytype,
                             String postalcode,
                             String coordinates,
                             String supportCount,
                             String downURl
    )
    {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://trap-cecfc.firebaseio.com");

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference ComplaintDB = database.getReference("ComplaintDB");

        DatabaseReference ComplaintID = ComplaintDB.child(complaintid);

        DatabaseReference ComplaintId = ComplaintID.child("ComplaintID");
        ComplaintId.setValue(complaintid);

        DatabaseReference UserID = ComplaintID.child("UserID");
        UserID.setValue(userid);

        DatabaseReference Category = ComplaintID.child("Category");
        Category.setValue(category);

        DatabaseReference Description = ComplaintID.child("Description");
        Description.setValue(description);

        DatabaseReference Solution = ComplaintID.child("Solution");
        Solution.setValue(solution);

        DatabaseReference State = ComplaintID.child("State");
        State.setValue(state);

        DatabaseReference City = ComplaintID.child("City");
        City.setValue(city);

        DatabaseReference Sta = ComplaintID.child("Status");
        Sta.setValue(status);

        DatabaseReference dt = ComplaintID.child("Timestamp");
        dt.setValue(timestamp);

        DatabaseReference wbd = ComplaintID.child("WaterBodyType");
        wbd.setValue(waterbodytype);

        DatabaseReference adr = ComplaintID.child("Address");
        adr.setValue(address);

        DatabaseReference coo = ComplaintID.child("Coordinates");
        coo.setValue(coordinates);

        DatabaseReference pc = ComplaintID.child("PinCode");
        pc.setValue(postalcode);

        DatabaseReference spc = ComplaintID.child("SupportCount");
        spc.setValue(supportCount);

        DatabaseReference dwn = ComplaintID.child("DownURL");
        dwn.setValue(downURl);
    }




}
