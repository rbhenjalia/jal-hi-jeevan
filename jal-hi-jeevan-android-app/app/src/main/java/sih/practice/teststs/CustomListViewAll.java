package sih.practice.teststs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CustomListViewAll extends RecyclerView.Adapter<CustomListViewAll.VH>
{
    Context context;
    String[] s,d,id,path;
    int l;

    public CustomListViewAll(Context context,String[] s,String[] d,String[] id,int l,String[] path)
    {
        this.context=context;
        this.s=s;
        this.d=d;
        this.id=id;
        this.l=l;
        this.path=path;
    }

    @Override
    public CustomListViewAll.VH onCreateViewHolder(ViewGroup parent, final int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        final CustomListViewAll.VH vh = new CustomListViewAll.VH(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),id[vh.getLayoutPosition()],Toast.LENGTH_SHORT).show();
                Intent in = new Intent(v.getContext(),ViewComplain.class);
                in.putExtra("CID",id[vh.getLayoutPosition()]);
                v.getContext().startActivity(in);
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(),"long "+id[vh.getLayoutPosition()],Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomListViewAll.VH vh, int position)
    {
        Log.d("AllCheck",""+d[position]+id[position]+s[position]+path[position]);
        vh.D.setText(d[position]);
        vh.ID.setText(id[position]);
        vh.L.setText(s[position]);
        new DownloadImageTask2(vh.img)
                .execute(path[position]);
    }

    @Override
    public int getItemCount(){
        return l;
    }
    class VH extends RecyclerView.ViewHolder{
        TextView ID,L,D;
        ImageView img;
        public VH(View view){
            super(view);
            ID=(TextView)view.findViewById(R.id.ComplaintTitle);
            L=(TextView)view.findViewById(R.id.ComplaintLocation);
            D=(TextView)view.findViewById(R.id.ComplaintDate);
            img=(ImageView)view.findViewById(R.id.RVimgA);
        }
    }
}
class DownloadImageTask2 extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    public DownloadImageTask2(ImageView bmImage) {
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
