package dfdashboardtest.com.myapplication.models;

import java.util.ArrayList;

/**
 * Created by pratap.kesaboyina on 30-11-2015.
 */
public class SectionDataModel {



    private String headerTitle;
    //private ArrayList<SingleItemModel> allItemsInSection;
    private ArrayList<App> mApps;



    public SectionDataModel() {

    }
    public SectionDataModel(String headerTitle, ArrayList<App> apps) {
        this.headerTitle = headerTitle;
        this.mApps = apps;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<App> getmApps() {
        return mApps;
    }

    public void setmApps(ArrayList<App> mApps) {
        this.mApps = mApps;
    }
}
