package br.com.ilhasoft.support.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

import androidx.annotation.DrawableRes;

/**
 * Created by johncordeiro on 7/23/15.
 */
public class ResourceUtil {

    private Context context;

    public ResourceUtil(Context context) {
        this.context = context;
    }

    public int getColorByAttr(int attrResource) {
        TypedValue typedValue = new TypedValue();

        TypedArray array = context.obtainStyledAttributes(typedValue.data, new int[] { attrResource });
        int color = array.getColor(0, 0);
        array.recycle();

        return color;
    }

    @DrawableRes
    public int getDrawableId(String drawable, int extra) {
        if(drawable != null)
            return context.getResources().getIdentifier(drawable, "drawable", context.getPackageName());
        return extra;
    }

}
