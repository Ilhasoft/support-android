package br.com.ilhasoft.support.tool;

import android.graphics.Color;

/**
 * Created by john-mac on 1/5/16.
 */
public class ColorHelper {

    public static int darkColor(int color) {
        return darkColor(color, 0.8f);
    }

    public static int darkColor(int color, float offset) {
        float[] hsv = new float[3];

        Color.colorToHSV(color, hsv);
        hsv[2] *= offset;

        return Color.HSVToColor(hsv);
    }

}
