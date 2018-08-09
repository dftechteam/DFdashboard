package dfdashboardtest.com.myapplication;

/**
 * Created by Admin on 20-07-2018.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Schedule_UpdationlistFragment extends Fragment implements OnChartValueSelectedListener
{

    int count=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frag_schedulelist_chart, container, false);

        PieChart pieChart = (PieChart) view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        Float collegecount_float= Float.valueOf("20").floatValue();

        Float studentcount_float= Float.valueOf("10").floatValue();

        ArrayList<PieEntry> yvalues2 = new ArrayList<PieEntry>();
        ArrayList<String> xVals2 = new ArrayList<String>();

        yvalues2.add(new PieEntry(studentcount_float, 0));
        yvalues2.add(new PieEntry(collegecount_float,1));
        PieDataSet dataSet2 = new PieDataSet(yvalues2, "");

        xVals2.add("No of Students");
        xVals2.add("No of Colleges");

        PieDataSet dataSet = new PieDataSet(yvalues2, "");
        PieData data = new PieData(dataSet);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
       /* pieChart.setDescription("Student and College Counts");
        pieChart.setDescriptionColor(Color.WHITE);
        pieChart.setDescriptionPosition(450,100);
        pieChart.setDescriptionTextSize(20f);*/
        pieChart.getLegend().setTextColor(Color.WHITE);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.DKGRAY);
       // pieChart.setOnChartValueSelectedListener(this);

        pieChart.animateXY(1400, 1400);

        // showActivity();
        return view;
    }
  /*  @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }*/

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " );
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}//end of fragment class
