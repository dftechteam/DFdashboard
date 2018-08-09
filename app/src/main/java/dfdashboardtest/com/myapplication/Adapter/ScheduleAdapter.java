package dfdashboardtest.com.myapplication.Adapter;

/**
 * Created by Admin on 22-06-2018.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dfdashboardtest.com.myapplication.Schedule_UpdationlistFragment;
import dfdashboardtest.com.myapplication.Schedule_updation_summaryFragment;

public class ScheduleAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ScheduleAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Schedule_updation_summaryFragment tab1 = new Schedule_updation_summaryFragment();
                return tab1;
            case 1:
                Schedule_UpdationlistFragment tab2 = new Schedule_UpdationlistFragment();
                return tab2;
           /* case 2:
                ChartThemeProjCountFragment tab3 = new ChartThemeProjCountFragment();
                return tab3;*/
          /*  case 3:
                ChartFundAmtCountFragment tab4 = new ChartFundAmtCountFragment();
                return tab4;*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}