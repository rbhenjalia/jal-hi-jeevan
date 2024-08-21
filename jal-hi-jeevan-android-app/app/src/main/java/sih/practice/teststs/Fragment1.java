package sih.practice.teststs;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    FirebaseDatabase database;
    DatabaseReference ref;

    String[] s,d,id,path,status,spc;
    RecyclerView recycle;
    ComplaintDetails cd;
    int a;
    String uid;
    public Fragment1() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Fragment1(String uid) {
        this.uid=uid;
    }
    int k;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_fragment1,container,false);
        cd = new ComplaintDetails();
        recycle=(RecyclerView)v.findViewById(R.id.rv1);

        database=FirebaseDatabase.getInstance();
        ref=database.getReferenceFromUrl("https://trap-cecfc.firebaseio.com/ComplaintDB");
        k=0;

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                a=(int)dataSnapshot.getChildrenCount();
                s=new String[a];
                d=new String[a];
                id=new String[a];
                path=new String[a];
                spc=new String[a];
                status=new String[a];
//                Log.d("Check",uid);
                for(DataSnapshot ds1:dataSnapshot.getChildren())
                {
                    if(ds1.child("UserID").getValue()!=null) {
                        String test = ds1.child("UserID").getValue().toString();
                        //Log.d("Check1", test + " " + String.valueOf(test.length()));
                        //Log.d("Check", uid + " " + String.valueOf(uid.length()));
                        if (uid.equals(test)) {
                            Log.d("Check", "True");
                            s[k] = ds1.child("State").getValue(String.class);
                            d[k] = ds1.child("Timestamp").getValue(String.class);
                            id[k] = ds1.child("ComplaintID").getValue(String.class);
                            path[k]=ds1.child("DownURL").getValue(String.class);
                            spc[k]=ds1.child("SupportCount").getValue(String.class);
                            status[k]=ds1.child("Status").getValue(String.class);
                            Log.d("Check", path[k]);
                            k++;
                        }
                    }
                }
                CustomListView rA = new CustomListView(v.getContext(),s,d,id,k,path,uid,spc,status);
                RecyclerView.LayoutManager rm = new GridLayoutManager(v.getContext(),1);
                DividerItemDecoration itemDecor = new DividerItemDecoration(v.getContext(), DividerItemDecoration.VERTICAL);
                recycle.addItemDecoration(itemDecor);
                recycle.setLayoutManager(rm);
                recycle.setAdapter(rA);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            int j;

        return v;
    }

}
