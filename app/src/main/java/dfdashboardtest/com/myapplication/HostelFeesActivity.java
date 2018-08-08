package dfdashboardtest.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class HostelFeesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostelfees);

        final Gauge gauge1 = findViewById(R.id.gauge1);
    }
}
