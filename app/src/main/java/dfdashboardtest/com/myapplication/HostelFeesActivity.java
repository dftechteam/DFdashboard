package dfdashboardtest.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class HostelFeesActivity extends Activity {


    Gauge gauge1,gauge2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostelfees);

        gauge1 = findViewById(R.id.gauge1);
        gauge2 =findViewById(R.id.gauge2);
        gauge1.setValue(85000);
        gauge2.setValue(75000);
    }





    public void onBackPressed()
    {
            Intent i = new Intent(HostelFeesActivity.this,DETMainActivity.class);
            startActivity(i);
            finish();

    }

}
