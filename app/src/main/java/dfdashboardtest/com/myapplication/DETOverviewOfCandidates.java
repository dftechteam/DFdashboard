package dfdashboardtest.com.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DETOverviewOfCandidates extends AppCompatActivity {

    private BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Spinner spin_year,spin_sandbox;
    private HorizontalBarChart mbarChart;
    private Context context;
    private HashMap<String,Integer> mapSandboxCode;
    private String str_year,str_sandbox;
    private ArrayList<String> arrlstApplicatn,arrlstAdmission,arrlstJoined,arrlstNotPlaced,arrlstCompleted,arrlstDropouts,arrlstFellowship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detoverview_candidates);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        // getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));


        View actionBarView = getSupportActionBar().getCustomView();
        TextView actionBarTV = (TextView) actionBarView.findViewById(R.id.txt_actionBar);
        actionBarTV.setText("DET-MIS");

        context = DETOverviewOfCandidates.this;

        spin_year = (Spinner) findViewById(R.id.spin_year);
        spin_sandbox = (Spinner) findViewById(R.id.spin_sandbox);
        mapSandboxCode = new HashMap<String,Integer>();

        arrlstApplicatn = new ArrayList<String>();
        arrlstAdmission = new ArrayList<String>();
        arrlstJoined = new ArrayList<String>();
        arrlstNotPlaced = new ArrayList<String>();
        arrlstCompleted = new ArrayList<String>();
        arrlstDropouts = new ArrayList<String>();
        arrlstFellowship = new ArrayList<String>();


        initializeSpinnerYear();
        initializeSpinnerSandbox();

        spin_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_year = spin_year.getSelectedItem().toString();
                str_sandbox = spin_sandbox.getSelectedItem().toString();

                if(str_year!="Select Year" && str_sandbox!="Select Sandbox"){
                    GetOverviewOfCandidates getOverviewOfCandidates = new GetOverviewOfCandidates(context);
                    getOverviewOfCandidates.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spin_sandbox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_year = spin_year.getSelectedItem().toString();
                str_sandbox = spin_sandbox.getSelectedItem().toString();

                if(str_year!="Select Year" && str_sandbox!="Select Sandbox"){
                    GetOverviewOfCandidates getOverviewOfCandidates = new GetOverviewOfCandidates(context);
                    getOverviewOfCandidates.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //initializeOverviewOfGraphs();
    }



    public class GetOverviewOfCandidates extends AsyncTask<Void, Void, SoapObject> {

        private AlertDialog alertDialog;
        private ProgressDialog progressDialog;


        GetOverviewOfCandidates (Context ctx){
            context = ctx;
            progressDialog = new ProgressDialog(context);
        }


        @Override
        protected SoapObject doInBackground(Void... params) {
            //String versionCode = (String) params[2];

            SoapObject response = getOverviewOfCandidates();

            Log.d("Soapresponseissssss",response.toString());

            return response;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(SoapObject result) {
            SoapPrimitive S_Status,S_Application,S_Admission,S_Joined,S_Not_Placed,S_Completed,S_Dropouts,S_Fellowship;
            Object O_Status,O_Application,O_Admission,O_Joined,O_Not_Placed,O_Completed,O_Dropouts,O_Fellowship;
            String str_Status=null,str_Application=null,str_Admission=null,str_Joined=null,str_Not_Placed=null,str_Completed=null,str_Dropouts=null,str_fellowship=null;

            for(int i=0;i < result.getPropertyCount();i++) {
                SoapObject list = (SoapObject) result.getProperty(i);

                O_Status = list.getProperty("Status");
                if (!O_Status.toString().equals("anyType{}") && !O_Status.toString().equals(null)) {
                    S_Status = (SoapPrimitive) list.getProperty("Status");
                    Log.d("Status:", S_Status.toString());
                    str_Status = S_Status.toString();
                }

                if(str_Status.equalsIgnoreCase("SUCESS")) {

                    O_Fellowship = list.getProperty("Fellowship");
                    if (!O_Fellowship.toString().equals("anyType{}") && !O_Fellowship.toString().equals(null)) {
                        S_Fellowship = (SoapPrimitive) list.getProperty("Fellowship");
                        Log.d("S_Fellowship", S_Fellowship.toString());
                        str_fellowship = S_Fellowship.toString();
                        arrlstFellowship.add(str_fellowship);
                    }


                    O_Application = list.getProperty("Applications");
                    if (!O_Application.toString().equals("anyType{}") && !O_Application.toString().equals(null)) {
                        S_Application = (SoapPrimitive) list.getProperty("Applications");
                        Log.d("S_Application", S_Application.toString());
                        str_Application = S_Application.toString();
                        arrlstApplicatn.add(str_Application);
                    }

                    O_Joined = list.getProperty("Joined");
                    if (!O_Joined.toString().equals("anyType{}") && !O_Joined.toString().equals(null)) {
                        S_Joined = (SoapPrimitive) list.getProperty("Joined");
                        Log.d("Joined", S_Joined.toString());
                        str_Joined = S_Joined.toString();
                        arrlstJoined.add(str_Joined);
                    }

                    O_Admission = list.getProperty("Current_Admission_Count");
                    if (!O_Admission.toString().equals("anyType{}") && !O_Admission.toString().equals(null)) {
                        S_Admission = (SoapPrimitive) list.getProperty("Current_Admission_Count");
                        Log.d("Admission", S_Admission.toString());
                        str_Admission = S_Admission.toString();
                        arrlstAdmission.add(str_Admission);
                    }

                    O_Not_Placed = list.getProperty("Not_Placed");
                    if (!O_Not_Placed.toString().equals("anyType{}") && !O_Not_Placed.toString().equals(null)) {
                        S_Not_Placed = (SoapPrimitive) list.getProperty("Not_Placed");
                        Log.d("Not Placed", S_Not_Placed.toString());
                        str_Not_Placed = S_Not_Placed.toString();
                        arrlstNotPlaced.add(str_Not_Placed);
                    }

                    O_Completed = list.getProperty("Certified");
                    if (!O_Completed.toString().equals("anyType{}") && !O_Completed.toString().equals(null)) {
                        S_Completed = (SoapPrimitive) list.getProperty("Certified");
                        Log.d("Completed", S_Completed.toString());
                        str_Completed = S_Completed.toString();
                        arrlstCompleted.add(str_Completed);
                    }

                    O_Dropouts = list.getProperty("Dropouts");
                    if (!O_Dropouts.toString().equals("anyType{}") && !O_Dropouts.toString().equals(null)) {
                        S_Dropouts = (SoapPrimitive) list.getProperty("Dropouts");
                        Log.d("DropOuts", S_Dropouts.toString());
                        str_Dropouts = S_Dropouts.toString();
                        arrlstDropouts.add(str_Dropouts);
                    }


                }


            }


            initializeOverviewOfGraphs();



       /*     O_Status = result.getProperty("Status");
            if(!O_Status.toString().equals("anyType{}") && !O_Status.toString().equals(null)){
                S_Status = (SoapPrimitive) result.getProperty("status");
                Log.d("Title",S_Status.toString());
                str_status = O_Status.toString();
            }*/

/*            if(!str_status.equals("Success")){
                lnrlyt_ProjectName.setVisibility(View.GONE);
                lnrlyt_projectType.setVisibility(View.GONE);
                lnrlyt_projObjective.setVisibility(View.GONE);
                lnrlyt_placeOfimpl.setVisibility(View.GONE);
                lnrlyt_leadFunded.setVisibility(View.GONE);
                lnrlyt_challanges.setVisibility(View.GONE);
                lnrlyt_uploadImage.setVisibility(View.GONE);
                lnrlyt_submit.setVisibility(View.GONE);
                lnrlyt_txtUploadProj.setVisibility(View.GONE);
                lnrlyt_uploadDocument.setVisibility(View.GONE);


                Toast.makeText(getActivity(),str_status,Toast.LENGTH_LONG).show();
            }
            else{
                lnrlyt_ProjectName.setVisibility(View.VISIBLE);
                lnrlyt_projectType.setVisibility(View.VISIBLE);
                lnrlyt_projObjective.setVisibility(View.VISIBLE);
                lnrlyt_placeOfimpl.setVisibility(View.VISIBLE);
                lnrlyt_leadFunded.setVisibility(View.VISIBLE);
                lnrlyt_challanges.setVisibility(View.VISIBLE);
                lnrlyt_uploadImage.setVisibility(View.VISIBLE);
                lnrlyt_submit.setVisibility(View.VISIBLE);
                lnrlyt_txtUploadProj.setVisibility(View.VISIBLE);
                lnrlyt_uploadDocument.setVisibility(View.VISIBLE);


                SoapPrimitive S_ProjectName,S_ProjectType,S_Beneficiaries,S_Objectives,S_LeadFunded,S_ApprovedAmount,S_Placeofimplement;
                Object O_ProjectName,O_ProjectType,O_Beneficiaries,O_Objectives,O_LeadFunded,O_ApprovedAmount,O_Placeofimplement;
                String str_ProjectName=null,str_ProjectType=null,str_Beneficiaries=null,str_Objectives,str_LeadFunded,str_ApprovedAmount,str_Placeofimplement;


                O_ProjectName = result.getProperty("Title");
                if(!O_ProjectName.toString().equals("anyType{}") && !O_ProjectName.toString().equals(null)){
                    S_ProjectName = (SoapPrimitive) result.getProperty("Title");
                    Log.d("Title:",S_ProjectName.toString());
                    str_ProjectName = S_ProjectName.toString();
                    txt_projectName.setText(str_ProjectName);
                }

                O_ProjectType = result.getProperty("ThemeName");
                if(!O_ProjectType.toString().equals("anyType{}") && !O_ProjectType.toString().equals(null)){
                    S_ProjectType = (SoapPrimitive) result.getProperty("ThemeName");
                    Log.d("Project Type",S_ProjectType.toString());
                    str_ProjectType = S_ProjectType.toString();
                    txt_projectType.setText(str_ProjectType);
                }

                O_Beneficiaries = result.getProperty("BeneficiaryNo");
                if(!O_Beneficiaries.toString().equals("anyType{}") && !O_Beneficiaries.toString().equals(null)){
                    S_Beneficiaries = (SoapPrimitive) result.getProperty("BeneficiaryNo");
                    Log.d("Beneficiaries:",S_Beneficiaries.toString());
                    str_Beneficiaries = S_Beneficiaries.toString();
                    txt_beneficiaries.setText(str_Beneficiaries);
                }

                O_Objectives = result.getProperty("Objectives");
                if(!O_Objectives.toString().equals("anyType{}") && !O_Objectives.toString().equals(null)){
                    S_Objectives = (SoapPrimitive) result.getProperty("Objectives");
                    Log.d("Objectives:",S_Objectives.toString());
                    str_Objectives = S_Objectives.toString();
                    txt_objective.setText(str_Objectives);
                }

                O_LeadFunded = result.getProperty("giventotal");
                if(!O_LeadFunded.toString().equals("anyType{}") && !O_LeadFunded.toString().equals(null)){
                    S_LeadFunded = (SoapPrimitive) result.getProperty("giventotal");
                    Log.d("Lead Funded:",S_LeadFunded.toString());
                    str_LeadFunded = S_LeadFunded.toString();
                    txt_leadFunded.setText(str_LeadFunded);
                }

                O_ApprovedAmount = result.getProperty("SanctionAmount");
                if(!O_ApprovedAmount.toString().equals("anyType{}") && !O_ApprovedAmount.toString().equals(null)){
                    S_ApprovedAmount = (SoapPrimitive) result.getProperty("SanctionAmount");
                    Log.d("Approved Amount:",S_ApprovedAmount.toString());
                    str_ApprovedAmount = S_ApprovedAmount.toString();
                    txt_approvedAmt.setText(str_ApprovedAmount);
                }

                O_Placeofimplement = result.getProperty("Placeofimplement");
                if(!O_Placeofimplement.toString().equals("anyType{}") && !O_Placeofimplement.toString().equals(null)){
                    S_Placeofimplement = (SoapPrimitive) result.getProperty("Placeofimplement");
                    Log.d("Placeofimplement:",S_Placeofimplement.toString());
                    str_Placeofimplement = S_Placeofimplement.toString();
                    txt_placeOfImpl.setText(str_Placeofimplement);
                }
            }*/

            progressDialog.dismiss();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private SoapObject getOverviewOfCandidates() {
        String METHOD_NAME = "GraphApplicationAdmission";
        String SOAP_ACTION1 = "http://mis.detedu.org/GraphApplicationAdmission";
        String NAMESPACE = "http://mis.detedu.org/";

        try{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            int sandboxId = mapSandboxCode.get(str_sandbox);


            request.addProperty("SandboxID",String.valueOf(sandboxId));
            request.addProperty("AcademicYear",str_year);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            Log.d("Requestissss",request.toString());
            HttpTransportSE androidHttpTransport = new HttpTransportSE("http://mis.detedu.org/DETServices.asmx?WSDL");
            try
            {
                androidHttpTransport.call(SOAP_ACTION1, envelope);

                SoapObject response = (SoapObject) envelope.getResponse();
                Log.e("soapcompletionxxxx",response.toString());
                return response;

            }
            catch(OutOfMemoryError ex){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context,"Slow Internet or Internet Dropped", Toast.LENGTH_LONG).show();
                    }
                });
            }

            catch (Exception t) {
                Log.e("request fail", "> " + t.getMessage().toString());
                final String exceptionStr = t.getMessage().toString();
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context,"Web Service Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        catch(OutOfMemoryError ex){
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(context,"Slow Internet or Internet Dropped", Toast.LENGTH_LONG).show();
                }
            });
        }


        catch (Exception t) {
            Log.e("exception outside",t.getMessage().toString());
            final String exceptionStr = t.getMessage().toString();
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(context,"Web Service Error", Toast.LENGTH_LONG).show();
                }
            });
        }
        return null;

    }

    private void initializeSpinnerSandbox() {
        final ArrayList<String> listSandbox = new ArrayList<String>();

        listSandbox.add("Select Sandbox");
        listSandbox.add("Hubballi");
        listSandbox.add("Nizamabad");
        listSandbox.add("Nalgonda");
        listSandbox.add("Cuddapa");


        mapSandboxCode.put("Hubballi",1);
        mapSandboxCode.put("Nizamabad",2);
        mapSandboxCode.put("Nalgonda",3);
        mapSandboxCode.put("Cuddapa",4);

/*        if(mapProjectIdProject.size()>0) {
            listCompletedProj.add("List Of Projects");

            Set<String> setCompleted = mapProjectIdProject.keySet();

            for (String key : setCompleted) {
                listCompletedProj.add(key);
            }
        }*/
        //ArrayAdapter dataAdapterSem = new ArrayAdapter(context, R.layout.simple_spinner_semester, listsemester);

        ArrayAdapter dataAdapterListSandbox = new ArrayAdapter(context, android.R.layout.simple_spinner_item, listSandbox);

        //ArrayAdapter dataAdapterListCompleted = ArrayAdapter.createFromResource(context, R.array.types, R.layout.simple_spinner_items);

        // Drop down layout style - list view with radio button
        // dataAdapterListCompleted.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterListSandbox.setDropDownViewResource(R.layout.spinnercustomstyle);

        // attaching data adapter to spinner
        spin_sandbox.setAdapter(dataAdapterListSandbox);
        //spin_sandbox.setSupportBackgroundTintList(ContextCompat.getColorStateList(context, R.color.colorWhite));

    }

    private void initializeSpinnerYear() {
        final ArrayList<String> listYear = new ArrayList<String>();

        listYear.add("Select Year");
        listYear.add("2017");
        listYear.add("2018");
/*        listSandbox.add("Nalgonda");
        listSandbox.add("Cuddapa");*/


/*        mapSandboxCode.put("Hubballi",1);
        mapSandboxCode.put("Nizamabad",2);
        mapSandboxCode.put("Nalgonda",3);
        mapSandboxCode.put("Cuddapa",4);*/

/*        if(mapProjectIdProject.size()>0) {
            listCompletedProj.add("List Of Projects");

            Set<String> setCompleted = mapProjectIdProject.keySet();

            for (String key : setCompleted) {
                listCompletedProj.add(key);
            }
        }*/
        //ArrayAdapter dataAdapterSem = new ArrayAdapter(context, R.layout.simple_spinner_semester, listsemester);

        ArrayAdapter dataAdapterListYear = new ArrayAdapter(context, android.R.layout.simple_spinner_item, listYear);

        //ArrayAdapter dataAdapterListCompleted = ArrayAdapter.createFromResource(context, R.array.types, R.layout.simple_spinner_items);

        // Drop down layout style - list view with radio button
        // dataAdapterListCompleted.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterListYear.setDropDownViewResource(R.layout.spinnercustomstyle);

        // attaching data adapter to spinner
        spin_year.setAdapter(dataAdapterListYear);
        //spin_year.setSupportBackgroundTintList(ContextCompat.getColorStateList(context, R.color.colorWhite));

    }

    private void initializeOverviewOfGraphs() {
        mChart = (BarChart) findViewById(R.id.barchart);
        mChart.setMaxVisibleValueCount(200);

        ArrayList<BarEntry> yValues = new ArrayList<>();

        //ArrayList<String> xVals = new ArrayList<>();

      /*  xVals.add("DKF");
        xVals.add("DSF");
        xVals.add("KFP");
        xVals.add("DCF");
        xVals.add("SEF");
        xVals.add("USP");
        xVals.add("DIT");
        xVals.add("CNC");
        xVals.add("ATP");
        xVals.add("DFP");
        xVals.add("SKC");
        xVals.add("DWP");
        xVals.add("DAT");
        xVals.add("DKM");
        xVals.add("HRM");
        xVals.add("SVP");*/

        XAxis xAxis = mChart.getXAxis();


        //mChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
        mChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrlstFellowship));

       /* for(int i=0;i<count;i++){
            float val1 = (float) (Math.random() * count) + 20;
            float val2 = (float) (Math.random() * count) + 20;
            float val3 = (float) (Math.random() * count) + 20;

            yValues.add(new BarEntry(i,new float[]{val1,val2,val3}));
        }*/



        float[] simpleFloat;
        for(int k=0;k<arrlstApplicatn.size();k++){
            simpleFloat = new float[arrlstApplicatn.size()];
            for(int l=0;l<arrlstApplicatn.size();l++) {
                if(l==0) {
                    simpleFloat[l] = Float.parseFloat(arrlstApplicatn.get(k).toString());
                }
                if(l==1){
                    simpleFloat[l] = Float.parseFloat(arrlstAdmission.get(k).toString());
                }
                if(l==2){
                    simpleFloat[l] = Float.parseFloat(arrlstJoined.get(k).toString());
                }
                if(l==3){
                    simpleFloat[l] = Float.parseFloat(arrlstNotPlaced.get(k).toString());
                }
                if(l==4){
                    simpleFloat[l] = Float.parseFloat(arrlstCompleted.get(k).toString());
                }
                if(l==5){
                    simpleFloat[l] = Float.parseFloat(arrlstDropouts.get(k).toString());
                }
            }

            yValues.add(new BarEntry(k, simpleFloat));
        }

/*        yValues.add(new BarEntry(0, new float[] {2305,801,471,414,153}));
        yValues.add(new BarEntry(1, new float[] {984,448,179,133}));
        yValues.add(new BarEntry(2, new float[] {787, 138}));
        yValues.add(new BarEntry(3, new float[] {498,191}));
        yValues.add(new BarEntry(4, new float[] {440,192,174}));
        yValues.add(new BarEntry(5, new float[] {394,221}));
        yValues.add(new BarEntry(6, new float[] {388,134}));
        yValues.add(new BarEntry(7, new float[] {357,159}));
        yValues.add(new BarEntry(8, new float[] {291,153}));
        yValues.add(new BarEntry(9, new float[] {246,128}));*/






















/*        yValues.add(new BarEntry(3, new float[] {15, 5, 1}));
        yValues.add(new BarEntry(4, new float[] {13, 4, 1}));
        yValues.add(new BarEntry(5, new float[] {10, 2, 1}));
        yValues.add(new BarEntry(6, new float[] {15, 10, 2}));
        yValues.add(new BarEntry(7, new float[] {15, 2, 1}));
        yValues.add(new BarEntry(8, new float[] {14, 3, 6}));
        yValues.add(new BarEntry(9, new float[] {15, 7, 2}));
        yValues.add(new BarEntry(10, new float[] {15, 3, 1}));
        yValues.add(new BarEntry(11, new float[] {11, 2, 4}));*/




        BarDataSet set1;

        set1 = new BarDataSet(yValues,"DET Students Statistics");
        set1.setDrawIcons(false);
        set1.setStackLabels(new String[]{"Applications","Admission","Joined","Not Placed","Completed","Dropouts"});
        set1.setColors(ColorTemplate.JOYFUL_COLORS);

        //set1.setColor(Color.rgb(217, 80, 138));
        //set1.setColors(ColorTemplate.createColors(Color.rgb(255,100,1)));


        mChart.getAxisLeft().setAxisMaximum(5000);
        mChart.getAxisLeft().setLabelCount(1000);

        XAxis xAxisss = mChart.getXAxis();
        xAxisss.setLabelCount(arrlstFellowship.size());




        mChart.getAxisLeft().setLabelCount(10);
        //yAxisss.setLabelCount();




        /*BarData data = new BarData(set1);
        data.setValueFormatter(new DETOverviewMyValueFormatter());*/

        BarData data = new BarData(set1);
        data.setValueFormatter(new DETOverviewMyValueFormatter());

        mChart.setDrawValueAboveBar(false);


        mChart.setData(data);
        mChart.setFitBars(true);
        mChart.invalidate();
        mChart.getDescription().setEnabled(false);



        mChart.setClickable(false);
        mChart.setEnabled(false);
        mChart.setDrawBarShadow(false);
        mChart.getData().setHighlightEnabled(false);
        mChart.setHighlightPerTapEnabled(false);
    }


    public void onBackPressed()
    {
        Intent i = new Intent(DETOverviewOfCandidates.this,HomeActivityNew.class);
        startActivity(i);
        finish();

    }

/*    private void initListOfCompletedProjectSpinner() {
        final ArrayList listCompletedProj = new ArrayList();
        if(mapProjectIdProject.size()>0) {
            listCompletedProj.add("List Of Projects");

            Set<String> setCompleted = mapProjectIdProject.keySet();

            for (String key : setCompleted) {
                listCompletedProj.add(key);
            }
        }else{
            listCompletedProj.add("No Approved Projects");
        }
        //ArrayAdapter dataAdapterSem = new ArrayAdapter(context, R.layout.simple_spinner_semester, listsemester);

        ArrayAdapter dataAdapterListCompleted = new ArrayAdapter(context, R.layout.simple_spinner_items, listCompletedProj);

        //ArrayAdapter dataAdapterListCompleted = ArrayAdapter.createFromResource(context, R.array.types, R.layout.simple_spinner_items);

        // Drop down layout style - list view with radio button
        // dataAdapterListCompleted.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterListCompleted.setDropDownViewResource(R.layout.spinnercustomstyle);

        // attaching data adapter to spinner
        spin_completedProject.setAdapter(dataAdapterListCompleted);
        spin_completedProject.setSupportBackgroundTintList(ContextCompat.getColorStateList(context, R.color.colorWhite));
    }*/
}
