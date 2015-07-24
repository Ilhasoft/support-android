package br.com.ilhasoft.support.tool;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.view.Window;
import android.view.WindowManager;

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

}
