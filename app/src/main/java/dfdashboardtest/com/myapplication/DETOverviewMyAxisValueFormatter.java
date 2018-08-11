package dfdashboardtest.com.myapplication;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by Shripad on 07-08-2018.
 */

public class DETOverviewMyAxisValueFormatter implements IAxisValueFormatter {
    private DecimalFormat mFormat;

    public DETOverviewMyAxisValueFormatter(){
        mFormat = new DecimalFormat("######.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        //return mFormat.format(value) + "$";
        return mFormat.format(value);
    }
}
