package br.com.ilhasoft.support.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TopCropImage extends ImageView{

    public TopCropImage(Context context) {
        super(context);
        setup();
    }

    public TopCropImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public TopCropImage(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        setScaleType(ScaleType.MATRIX);
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b)
    {
        Matrix matrix = getImageMatrix();
        float scaleFactor = getWidth()/(float)getDrawable().getIntrinsicWidth();
        matrix.setScale(scaleFactor, scaleFactor, 0, 0);
        setImageMatrix(matrix);
        return super.setFrame(l, t, r, b);
    }

}
