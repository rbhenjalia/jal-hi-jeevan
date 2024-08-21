package sih.practice.teststs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class pie_chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        //Total problem fetched from DB
        /*int tot=100;
        int sol=25;

        int unsol=tot-sol;*/
        FirebaseDatabase.getInstance().getReference("/ComplaintDB/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PieChart pieChart = (PieChart) findViewById(R.id.piechart);
                pieChart.setUsePercentValues(true);

                long x = dataSnapshot.getChildrenCount();

                int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String stat = postSnapshot.child("Status").getValue().toString();

                    if (stat.equals("Solved")) {
                        i = i + 1;
                    }
                }

                String v = String.valueOf(i);
                //Toast.makeText(getApplicationContext(), v, Toast.LENGTH_SHORT).show();


                int tot = (int) x;
                String p = String.valueOf(tot);
                //Toast.makeText(getApplicationContext(), p, Toast.LENGTH_SHORT).show();

                int sol = i;

                int unsol = tot - sol;
                float solved = (float) sol * (100 / unsol + sol);
                float unsolved = (float) unsol * (100 / unsol + sol);
                ArrayList<Entry> yvalues = new ArrayList<Entry>();
                yvalues.add(new Entry(solved, 0));
                yvalues.add(new Entry(unsolved, 1));

                PieDataSet dataSet = new PieDataSet(yvalues, "Results");

                ArrayList<String> xVals = new ArrayList<String>();
                xVals.add("Solved");
                xVals.add("Unsolved");
                PieData data = new PieData(xVals, dataSet);


                data.setValueFormatter(new PercentFormatter());

                pieChart.setData(data);
                dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                data.setValueTextSize(20f);
                pieChart.setDescription("");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}


