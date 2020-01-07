package br.com.ilhasoft.support.tool;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by johndalton on 10/09/14.
 */
public class ScreenUtils {

    private AppCompatActivity activity;

    public ScreenUtils(AppCompatActivity activity) {
        this.activity = activity;
    }

    public int getNavigationBarSize() {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return (int) resources.getDimension(resourceId);//TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, resources.getDimension(resourceId), resources.getDisplayMetrics());
        }

        return 0;
    }

    public boolean isFullscreen() {
        Window window = activity.getWindow();
        if(window == null)
            return false;

        View decorView = window.getDecorView();

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            return (decorView.getSystemUiVisibility() & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) != 0;
        } else {
            return (window.getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
        }
    }

    public void showSystemUI() {
        if(activity == null) return;

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            showSystemUiKitKat(activity);
        } else {
            showSystemUiICS(activity);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showSystemUiKitKat(Activity activity) {
        Window window = activity.getWindow();
        if(window == null)
            return;

        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    private void showSystemUiICS(AppCompatActivity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private boolean showActionBar(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar == null)
            return true;

        actionBar.show();
        return false;
    }

    public void hideSystemUI() {
        if(activity == null) return;

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            hideSystemUiKitKat(activity);
        } else {
            hideSystemUiICS(activity);
        }

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideSystemUiKitKat(Activity activity) {
        Window window = activity.getWindow();
        if(window == null)
            return;

        View decorView = window.getDecorView();

        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                      | View.SYSTEM_UI_FLAG_FULLSCREEN
                                      | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void hideSystemUiICS(AppCompatActivity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private boolean hideActionBar(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar == null)
            return true;

        actionBar.hide();
        return false;
    }

}
