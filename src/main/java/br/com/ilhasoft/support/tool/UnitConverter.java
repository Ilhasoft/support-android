package br.com.ilhasoft.ilhaandroid.tool;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by johndalton on 23/01/15.
 */
public class UnitConverter {

    private Context context;

    public UnitConverter(Context context) {
        this.context = context;
    }

    public float convertDpToPx(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public float convertPxToDp(float px) {
        return px / (context.getResources().getDisplayMetrics().densityDpi / 160);
    }

}
