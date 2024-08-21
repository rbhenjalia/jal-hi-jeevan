package sih.practice.teststs;

/**
 * Created by Akshar on 3/20/2018.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListView1 extends BaseAdapter
{
    Context context;
    String newstitles[];
    String newsdata[];
    LayoutInflater inflter;

    public CustomListView1(Context applicationContext, String[] nts , String[] nws)
    {
    context = context;
    newstitles = nts;
    newsdata = nws;
    inflter = (LayoutInflater.from(applicationContext));
}

    @Override
    public int getCount() {
        return newstitles.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int i, View view, ViewGroup viewGroup )
    {
        view=inflter.inflate(R.layout.listitem1,null);

        TextView NewsTitle=(TextView)view.findViewById(R.id.NewsTitle);
        TextView Newsdata=(TextView)view.findViewById(R.id.Newsdata);
        NewsTitle.setText(newstitles[i]);
        Newsdata.setText(newsdata[i]);
        return view;
    }

}
