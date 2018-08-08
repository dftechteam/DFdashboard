package dfdashboardtest.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DETMainActivity extends AppCompatActivity {


    Button detfees_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detmain);


        detfees_bt =(Button)findViewById(R.id.detfees_BT);













        detfees_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DETMainActivity.this,HostelFeesActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
