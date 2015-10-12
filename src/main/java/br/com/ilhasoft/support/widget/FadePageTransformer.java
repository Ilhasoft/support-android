package br.com.ilhasoft.support.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Parallax transformer for ViewPagers that let you set different parallax
 * effects for each view in your Fragments.
 *
 * Created by Marcos Trujillo (#^.^#) on 1/10/14.
 */
public class FadePageTransformer implements ViewPager.PageTransformer {

    private List<TransformInformation> mViewsToFade = new ArrayList<>();

    public FadePageTransformer() {
    }

    public FadePageTransformer addViewToFade(TransformInformation viewInfo) {
        mViewsToFade.add(viewInfo);
        return this;
    }

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) {
            ViewCompat.setAlpha(view, 0);
        } else if (position <= 1) { // [-1,1]
            ViewCompat.setAlpha(view, 1);
            ViewCompat.setTranslationX(view, pageWidth * -position);

            if(view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                applyEffectToChildren(position, pageWidth, viewGroup);
            }
        } else {
            ViewCompat.setAlpha(view, 0);
        }
    }

    private void applyEffectToChildren(float position, int pageWidth, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);

            if(!(childView instanceof ViewGroup)) {
                int viewToFade;
                if((viewToFade = mViewsToFade.indexOf(new TransformInformation(childView.getId()))) != -1) {
                    applyFadeEffect(childView, position, mViewsToFade.get(viewToFade));
                } else {
                    ViewCompat.setTranslationX(childView, pageWidth * position);
                }
            } else {
                applyEffectToChildren(position, pageWidth, (ViewGroup)childView);
            }
        }
    }

    private void applyFadeEffect(View view, float position, TransformInformation transformInformation) {
        View viewFromResource = getViewFromResource(view, transformInformation);
        if(viewFromResource != null) {
            ViewCompat.setAlpha(viewFromResource, 1.0F - Math.abs(position));
        }
    }

    private View getViewFromResource(View view, TransformInformation information) {
        View viewFromResource;
        Object tag = view.getTag(information.resource);
        if(tag != null) {
            viewFromResource = (View) tag;
        } else {
            viewFromResource = view.findViewById(information.resource);
            view.setTag(information.resource, viewFromResource);
        }
        return viewFromResource;
    }

    /**
     * Information to make the parallax effect in a concrete view.
     *
     * parallaxEffect positive values reduces the speed of the view in the translation
     * ParallaxEffect negative values increase the speed of the view in the translation
     * Try values to see the different effects. I recommend 2, 0.75 and 0.5
     */
    public static class TransformInformation {

        int resource = -1;

        public TransformInformation(int resource) {
            this.resource = resource;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TransformInformation that = (TransformInformation) o;
            return resource == that.resource;

        }

        @Override
        public int hashCode() {
            return resource;
        }
    }
}
