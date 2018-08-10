package dfdashboardtest.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import dfdashboardtest.com.myapplication.Adapter.RecyclerViewDataAdapter;
import dfdashboardtest.com.myapplication.models.App;
import dfdashboardtest.com.myapplication.models.SectionDataModel;

public class HomeActivityNew extends AppCompatActivity {

    private Toolbar toolbar;

    ArrayList<SectionDataModel> allSampleData;
    static ArrayList<String> wishlistImageUri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_new);

      //  toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
       // getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));


        View actionBarView = getSupportActionBar().getCustomView();
        TextView actionBarTV = (TextView) actionBarView.findViewById(R.id.txt_actionBar);
        actionBarTV.setText("Analytical Dashboard");

        allSampleData = new ArrayList<SectionDataModel>();



      /*  if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("Management");

        }
*/

        //createDummyData();

        detView();
        mainView();
     //   sandboxView();

        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);


    }

   /* public void createDummyData() {
        for (int i = 1; i <= 2; i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle("Section " + i);

          *//*  ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 8; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }*//*
            ArrayList<App> singleItem = getApps();
            //singleItem=getApps();
            dm.setmApps(singleItem);

            allSampleData.add(dm);

        }
    }*/

    public void detView() {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("DET Applications");
        ArrayList<App> viewdData = getApps_det();
        dm.setmApps(viewdData);
        allSampleData.add(dm);
    }
    public void mainView() {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("DF Applications");
        ArrayList<App> viewdData = getApps_df();
        dm.setmApps(viewdData);
        allSampleData.add(dm);
    }
   /* public void sandboxView() {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("SandBox");
        ArrayList<App> viewdData = getApps();
        dm.setmApps(viewdData);
        allSampleData.add(dm);
    }*/
   /* private ArrayList<App> getApps() {
        ArrayList<App> apps = new ArrayList<>();
        apps.add(new App("ACADEMICS", R.drawable.academics_new, 4.6f));
        apps.add(new App("ACCOUNT", R.drawable.account_new, 4.8f));
        apps.add(new App("HOSTEL", R.drawable.hostal_new, 4.5f));
        apps.add(new App("HR", R.drawable.hr_new, 4.2f));
        apps.add(new App("MANAGEMENT", R.drawable.management_new, 4.6f));
        apps.add(new App("MARKETING", R.drawable.marketing_new, 3.9f));
        apps.add(new App("S & O", R.drawable.so_new, 4.6f));
        apps.add(new App("STUDENTS", R.drawable.students_new, 4.2f));
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

    public void addWishlistImageUri(String wishlistImageUri) {
        this.wishlistImageUri.add(0,wishlistImageUri);
    }

    public void removeWishlistImageUri(int position) {
        this.wishlistImageUri.remove(position);
    }

}
