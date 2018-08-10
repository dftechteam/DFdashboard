package dfdashboardtest.com.myapplication;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by Shripad on 07-08-2018.
 */

public class DETOverviewMyValueFormatter implements IValueFormatter {
    private DecimalFormat mFormat;

    public DETOverviewMyValueFormatter(){
        mFormat = new DecimalFormat("######.#");
    }


    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        //return mFormat.format(value) + "$";
        return mFormat.format(value);
    }
}
