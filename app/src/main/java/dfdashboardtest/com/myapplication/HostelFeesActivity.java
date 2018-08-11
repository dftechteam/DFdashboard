package dfdashboardtest.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HostelFeesActivity extends AppCompatActivity {


    Gauge gauge1,hostel_gauge2,girlshostel_gauge,boyshostel_gauge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostelfees);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        // getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));


        View actionBarView = getSupportActionBar().getCustomView();
        TextView actionBarTV = (TextView) actionBarView.findViewById(R.id.txt_actionBar);
        actionBarTV.setText("HOSTEL");

        gauge1 = findViewById(R.id.gauge1);
        hostel_gauge2 =findViewById(R.id.totalhostel_gauge2);
        girlshostel_gauge =findViewById(R.id.girlshostel_gauge);
       boyshostel_gauge= findViewById(R.id.boyshostel_gauge);
       // gauge1.setValue(85000);
        hostel_gauge2.setValue(75000);
        girlshostel_gauge.setValue(35000);
        boyshostel_gauge.setValue(35000);



        HandlerThread thread = new HandlerThread("GaugeDemoThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gauge1.moveToValue(65000);
            }
        }, 100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gauge1.moveToValue(85000);
            }
        }, 1000);




        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hostel_gauge2.moveToValue(160000);
            }
        }, 100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hostel_gauge2.moveToValue(75000);
            }
        }, 1000);





    }//Oncreate()





    public void onBackPressed()
    {
            Intent i = new Intent(HostelFeesActivity.this,HomeActivityNew.class);
            startActivity(i);
            finish();

    }

}
