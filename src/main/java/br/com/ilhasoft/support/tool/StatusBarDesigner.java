package br.com.ilhasoft.support.tool;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;

/**
 * Created by johncordeiro on 7/22/15.
 */
public class StatusBarDesigner {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColorById(Activity activity, @ColorRes int color) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(Activity activity, int color) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setStatusBarTransparent(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

}
