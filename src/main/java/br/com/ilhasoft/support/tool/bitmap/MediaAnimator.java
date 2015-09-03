package br.com.ilhasoft.support.tool.bitmap;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import br.com.ilhasoft.support.R;

/**
 * Created by johndalton on 22/08/14.
 */
public class MediaAnimator {

    private Context context;

    public MediaAnimator (Context context) {
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showMedia(ImageView imageView) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Animator alphaAnimator = AnimatorInflater.loadAnimator(context, R.animator.media_in);
            alphaAnimator.setTarget(imageView);
            alphaAnimator.start();
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setDuration(100);
            imageView.startAnimation(alphaAnimation);
        }
    }

}
