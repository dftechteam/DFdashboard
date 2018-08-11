package dfdashboardtest.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import dfdashboardtest.com.myapplication.Adapter.AdapterGrid;
import dfdashboardtest.com.myapplication.models.App;


public class Grid_homescreen_Activity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    AdapterGrid adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_homescreen_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        // getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));

        View actionBarView = getSupportActionBar().getCustomView();
        TextView actionBarTV = (TextView) actionBarView.findViewById(R.id.txt_actionBar);
        actionBarTV.setText("Analytical Dashboard");

        Intent intent = getIntent();
        String sectionName = intent.getStringExtra("sectionName");
        Log.i("tag","sectionName"+sectionName);


        if(sectionName.equals("DF Applications")) {
            adapter = new AdapterGrid(false, false, getApps_df());
        }
        else if(sectionName.equals("DET Applications")) {
            adapter = new AdapterGrid(false, false, getApps_det());
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    /*private ArrayList<App> getApps() {
        ArrayList<App> apps = new ArrayList<>();
        apps.add(new App("ACADEMICS", R.drawable.academics));
        apps.add(new App("ACCOUNT", R.drawable.accounts));
        apps.add(new App("HOSTEL", R.drawable.hostal));
        apps.add(new App("HR", R.drawable.hr));
        apps.add(new App("MANAGEMENT", R.drawable.management));
        apps.add(new App("MARKETING", R.drawable.marketing));
        apps.add(new App("S & O", R.drawable.so));
        apps.add(new App("STUDENTS", R.drawable.students));
        return apps;
    }*/

    private ArrayList<App> getApps_det() {
        ArrayList<App> apps = new ArrayList<>();
        apps.add(new App("DET-MIS", R.drawable.marketing_new, 3.9f));
        apps.add(new App("SCHEDULER", R.drawable.academics_new, 4.6f));
        apps.add(new App("HOSTEL", R.drawable.hostal_new, 4.5f));
        apps.add(new App("DET-HRMS", R.drawable.hr_new, 4.2f));
        return apps;
    }
    private ArrayList<App> getApps_df() {
        ArrayList<App> apps = new ArrayList<>();
        apps.add(new App("DF-HRMS", R.drawable.hr_new, 4.2f));
        apps.add(new App("LEAD", R.drawable.management_new, 4.6f));
        apps.add(new App("BCI", R.drawable.marketing_new, 3.9f));
        apps.add(new App("Navodyami", R.drawable.so_new, 4.6f));
        apps.add(new App("Asset Tracker", R.drawable.students_new, 4.2f));
        apps.add(new App("Grants", R.drawable.account_new, 4.8f));
        apps.add(new App("Farmpond", R.drawable.hostal_new, 4.5f));
        return apps;
    }



}
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_homescreen_activity);
    }
}
*/