package sih.practice.teststs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class News extends AppCompatActivity
{
    TextView t1,t2,t3,t4,t5,t6,t7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        t1= (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView1);
        t3 = (TextView) findViewById(R.id.textView2);
        t4 = (TextView) findViewById(R.id.textView3);
        t5 = (TextView) findViewById(R.id.textView4);
        t6 = (TextView) findViewById(R.id.textView5);
        t7 = (TextView) findViewById(R.id.textView6);
    }
    public void nam(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nmcg.nic.in/NamamiGanga.aspx"));
        startActivity(i);
    }
    public void t1(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.worldbank.org/en/news/feature/2015/03/23/india-the-national-ganga-river-basin-project"));
        startActivity(i);
    }
    public void t2(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.swachhbharaturban.in/sbm/home/"));
        startActivity(i);
    }
    public void t3(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pib.nic.in/newsite/PrintRelease.aspx?relid=161397"));
        startActivity(i);
    }
    public void t4(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://currentaffairs.gktoday.in/union-government-launches-jal-kranti-abhiyan-06201523369.html"));
        startActivity(i);
    }
    public void t5(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.indiawaterweek.in/"));
        startActivity(i);
    }
    public void t6(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://in.reuters.com/article/india-rivers/modis-87-billion-river-linking-gamble-set-to-take-off-as-floods-hit-india-idINKCN1BC4SF"));
        startActivity(i);
    }
}
