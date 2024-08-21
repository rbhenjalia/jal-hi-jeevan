package sih.practice.teststs;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ComplaintDetails {
    FirebaseDatabase fd;
    public String s,id,d;
    public void test(String Id) {
        fd = FirebaseDatabase.getInstance();
        fd.getReference().child("/ComplaintDB/"+Id+"/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> td = (HashMap<String,String>)dataSnapshot.getValue();

                setDate(td.get("Date"));
                setID(td.get("ComplaintID"));
                setState(td.get("State"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void setDate(String d)
    {
        this.d=d;
    }
    public void setID(String id)
    {
        this.id=id;
    }
    public void setState(String s)
    {
        this.s=s;
    }

    public String getDate()
    {
        return d;
    }
    public String getState()
    {
        return s;
    }
    public String getID()
    {
        return id;
    }
}
