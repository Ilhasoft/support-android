package br.com.ilhasoft.support.tool;

import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by john-mac on 2/5/16.
 */
public class ButtonTinter {

    public static void setImageButtonTint(ImageButton button, ColorStateList tint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && button instanceof AppCompatImageButton) {
            ((AppCompatImageButton) button).setSupportBackgroundTintList(tint);
        } else {
            ViewCompat.setBackgroundTintList(button, tint);
        }
    }

}
