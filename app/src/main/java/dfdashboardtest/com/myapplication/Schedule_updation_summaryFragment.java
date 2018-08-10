package dfdashboardtest.com.myapplication;

/**
 * Created by Admin on 20-07-2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

public class Schedule_updation_summaryFragment extends Fragment implements OnChartValueSelectedListener
{

    int count=0;
    String str_Status=null,str_Engaged_Per=null,str_Not_Engaged_Per=null,str_Not_Updated_Per=null,str_Not_Assigned_Per=null;
    PieChart pieChart;
    Spinner sandbox_spin,year_spin;
    ArrayAdapter sandboxAdapter,yearAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frag_schedulesummary_chart, container, false);

        GetScheduleSummary getScheduleSummary=new GetScheduleSummary(getActivity());
        getScheduleSummary.execute();

        sandbox_spin=(Spinner) view.findViewById(R.id.sandbox_spin);
        year_spin=(Spinner) view.findViewById(R.id.year_spin);

      //  String [] values_sandbox = {"Hubballi","Nizamabad","Nalgonda"};

        List<String> values_sandbox = new ArrayList<String>();
        values_sandbox.add("Automobile");
        values_sandbox.add("Business Services");
        values_sandbox.add("Computers");
        values_sandbox.add("Education");
        values_sandbox.add("Personal");
        values_sandbox.add("Travel");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, values_sandbox);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sandbox_spin.setAdapter(adapter);

        String [] values_year = {"2018","2017"};

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, values_year);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spin.setAdapter(adapter_year);


        pieChart = (PieChart) view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        
        Float collegecount_float= Float.valueOf("20").floatValue();
        Float studentcount_float= Float.valueOf("10").floatValue();

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

    public class GetScheduleSummary extends AsyncTask<Void, Void, SoapObject> {

        Context context;
        //AlertDialog alertDialog;
        private ProgressDialog progressDialog;

        //private ProgressBar progressBar;

        GetScheduleSummary (Context ctx){
            context = ctx;
            progressDialog = new ProgressDialog(context);
        }


        @Override
        protected SoapObject doInBackground(Void... params) {
            //String str_leadId = (String) params [0];
            //String versionCode = (String) params[2];

            SoapObject response = getThemeWiseProjCount();

            //Log.d("GetThemeWiseProjCout",response.toString());

            return response;
        }

        @Override
        protected void onPreExecute() {
         /*   progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);*/

            progressDialog.setMessage("Loading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(SoapObject result) {

            Log.d("tag","GraphScheduleUpdationSummary"+result.toString());

            SoapPrimitive S_Status,S_Engaged_Per,S_Not_Engaged_Per,S_Not_Updated_Per,S_Not_Assigned_Per;
            Object O_Status,O_Engaged_Per,O_Not_Engaged_Per,O_Not_Updated_Per,O_Not_Assigned_Per;

            for(int i=0;i < result.getPropertyCount();i++) {
                SoapObject list = (SoapObject) result.getProperty(i);

                Log.d("finalStringssssss",list.toString());
                SoapPrimitive indivisualObject = (SoapPrimitive) list.getProperty("Status");

                O_Status = list.getProperty("Status");
                if (!O_Status.toString().equals("anyType{}") && !O_Status.toString().equals(null)) {
                    S_Status = (SoapPrimitive) list.getProperty("Status");
                    Log.d("Status:", S_Status.toString());
                    str_Status = S_Status.toString();
                }

                if(str_Status.equalsIgnoreCase("SUCESS")) {
                    O_Engaged_Per = list.getProperty("Engaged_Per");
                    if (!O_Engaged_Per.toString().equals("anyType{}") && !O_Engaged_Per.toString().equals(null)) {
                        S_Engaged_Per = (SoapPrimitive) list.getProperty("Engaged_Per");
                        Log.d("Engaged_Per", S_Engaged_Per.toString());
                        str_Engaged_Per = S_Engaged_Per.toString();
                    }

                    O_Not_Engaged_Per = list.getProperty("Not_Engaged_Per");
                    if (!O_Not_Engaged_Per.toString().equals("anyType{}") && !O_Not_Engaged_Per.toString().equals(null)) {
                        S_Not_Engaged_Per = (SoapPrimitive) list.getProperty("Not_Engaged_Per");
                        Log.d("Not_Assigned_Per", S_Not_Engaged_Per.toString());
                        str_Not_Engaged_Per = S_Not_Engaged_Per.toString();
                    }

                    O_Not_Updated_Per = list.getProperty("Not_Updated_Per");
                    if (!O_Not_Updated_Per.toString().equals("anyType{}") && !O_Not_Updated_Per.toString().equals(null)) {
                        S_Not_Updated_Per = (SoapPrimitive) list.getProperty("Not_Updated_Per");
                        Log.d("Not_Updated_Per", S_Not_Updated_Per.toString());
                        str_Not_Updated_Per = S_Not_Updated_Per.toString();
                    }

                    O_Not_Assigned_Per = list.getProperty("Not_Assigned_Per");
                    if (!O_Not_Assigned_Per.toString().equals("anyType{}") && !O_Not_Assigned_Per.toString().equals(null)) {
                        S_Not_Assigned_Per = (SoapPrimitive) list.getProperty("Not_Assigned_Per");
                        Log.d("Not_Assigned_Per", S_Not_Assigned_Per.toString());
                        str_Not_Assigned_Per = S_Not_Assigned_Per.toString();
                    }

                    Log.i("tag","Not_Updated_Per="+str_Not_Updated_Per+"str_Not_Engaged_Per"+str_Not_Engaged_Per);
                    Log.i("tag","str_Engaged_Per="+str_Engaged_Per+"str_Not_Assigned_Per"+str_Not_Assigned_Per);

                   /* if (str_ThemeName != null && !str_ThemeName.isEmpty() && str_ThemeName != "" && !str_ThemeName.equals("anyType{}") && str_ThemeName != "{}") {
                        //txt.setText(str_ThemeName);
                        Log.d("ThemeNameIsss",str_ThemeName);
                        listThemeName.add(str_ThemeName);
                    }

                    if (str_Counts != null && !str_Counts.isEmpty() && str_Counts != "" && !str_Counts.equals("anyType{}") && str_Counts != "{}") {
                        //txt.setText(str_ThemeName);
                        Log.d("Countssissss",str_Counts);
                        listCount.add(str_Counts);
                    }*/

      /*          if (str_StudCollegeName != null && !str_StudCollegeName.isEmpty() && str_StudCollegeName != "" && !str_StudCollegeName.equals("anyType{}") && str_StudCollegeName != "{}") {
                    txt_studCollegeName.setText(str_StudCollegeName);
                }

                if (str_StudLeadId != null && !str_StudLeadId.isEmpty() && str_StudLeadId != "" && !str_StudLeadId.equals("anyType{}") && str_StudLeadId != "{}") {
                    txt_leadId.setText(str_StudLeadId);
                }

                if (str_StudType != null && !str_StudType.isEmpty() && str_StudType != "" && !str_StudType.equals("anyType{}") && str_StudType != "{}") {
                    txt_studType.setText(str_StudType);
                }
*/
                }

            }

       //     multiBarchartsInOne();

            pieChartFunction();
            progressDialog.dismiss();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private void pieChartFunction(){
        Float Engaged_Per_float= Float.valueOf(str_Engaged_Per).floatValue();
        Float Not_Engaged_Per_float= Float.valueOf(str_Not_Engaged_Per).floatValue();
        Float Not_Updated_Per_float= Float.valueOf(str_Not_Updated_Per).floatValue();
        Float Not_Assigned_Per_float= Float.valueOf(str_Not_Assigned_Per).floatValue();

        ArrayList<PieEntry> yvalues2 = new ArrayList<PieEntry>();
        ArrayList<String> xVals2 = new ArrayList<String>();

        yvalues2.add(new PieEntry(Engaged_Per_float, 0));
        yvalues2.add(new PieEntry(Not_Engaged_Per_float,1));
        yvalues2.add(new PieEntry(Not_Updated_Per_float, 2));
        yvalues2.add(new PieEntry(Not_Assigned_Per_float,3));

        PieDataSet dataSet2 = new PieDataSet(yvalues2, "");

        xVals2.add("Engaged Class");
        xVals2.add("Not_Engaged");
        xVals2.add("Not_Updated_Per");
        xVals2.add("Not_Assigned_Per");

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

    }
    private SoapObject getThemeWiseProjCount()
    {

        String METHOD_NAME = "GraphScheduleUpdationSummary";
        String SOAP_ACTION1 = "http://mis.detedu.org/GraphScheduleUpdationSummary";

        try{
            SoapObject request = new SoapObject("http://mis.detedu.org/", METHOD_NAME);

            request.addProperty("SandboxID", "1");
            request.addProperty("AcademicYear", "2018");
            request.addProperty("Email", "madhushree.tech@detedu.org");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //SoapSerializationEnvelope evp = new SoapSoapEnvelope.XSD;

            envelope.dotNet = true;
            //Set output SOAP object
            envelope.setOutputSoapObject(request);
            //Create HTTP call object
            //envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            HttpTransportSE androidHttpTransport = new HttpTransportSE("http://mis.detedu.org/DETServices.asmx?WSDL");
            //androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

            try
            {
                androidHttpTransport.call(SOAP_ACTION1, envelope);
                Log.d("soap responseyyyyyyy",envelope.getResponse().toString());
                SoapObject response = (SoapObject) envelope.getResponse();
                Log.d("soap responseyyyyyyy",response.toString());

                return response;

            }
            catch (Exception t) {
                Log.e("request fail", "> " + t.getMessage().toString());
            }
        }catch (Exception t) {
            Log.d("exception outside",t.getMessage().toString());
        }
        return null;


    }
}//end of fragment class
