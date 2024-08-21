package sih.practice.teststs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.io.InputStream;

public class CustomListView  extends RecyclerView.Adapter<CustomListView.VH>
{
    Context context;
    String[] s,d,id,path,spc,status;
    int l;
    String u;

    public CustomListView(Context context,String[] s,String[] d,String[] id,int l,String[] path,String u,String[] spc,String[] status)
    {
        this.context=context;
        this.s=s;
        this.d=d;
        this.id=id;
        this.l=l;
        this.path=path;
        this.u=u;
        this.status=status;
        this.spc=spc;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, final int viewType){
        final View v = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
       final VH vh = new VH(v);
       v.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(v.getContext(),id[vh.getLayoutPosition()],Toast.LENGTH_SHORT).show();
               Intent in = new Intent(v.getContext(),ViewComplain.class);
               in.putExtra("CID",id[vh.getLayoutPosition()]);
               in.putExtra("UID",u);
               v.getContext().startActivity(in);
           }
       });
       v.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(final View v) {
               return true;
           }
       });

        return vh;

    }

    @Override
    public void onBindViewHolder(CustomListView.VH vh, int position)
    {
        vh.D.setText(d[position]);
        vh.ID.setText(id[position]);
        vh.L.setText(s[position]);
        //Log.d("Check2",path[position].toString());
        new DownloadImageTask1(vh.img)
                .execute(path[position]);
        vh.St.setText("Status: "+status[position]);
        vh.Sc.setText("Support count: "+spc[position]);
    }

    @Override
    public int getItemCount(){
        return l;
    }
    class VH extends RecyclerView.ViewHolder{
        TextView ID,L,D,St,Sc;
        ImageView img;
        public VH(View view){
            super(view);
            ID=(TextView)view.findViewById(R.id.UComplaintTitle);
            L=(TextView)view.findViewById(R.id.UComplaintLocation);
            D=(TextView)view.findViewById(R.id.UComplaintDate);
            img=(ImageView)view.findViewById(R.id.RVimg);
            St=(TextView)view.findViewById(R.id.UStatus);
            Sc=(TextView)view.findViewById(R.id.USupportNumber);
        }
    }
}
class DownloadImageTask1 extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    public DownloadImageTask1(ImageView bmImage) {
        this.bmImage = bmImage;
    }
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
