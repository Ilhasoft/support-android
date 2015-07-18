package br.com.ilhasoft.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import br.com.ilhasoft.support.R;

public class Ring extends View {

    private static final String TAG = "Ring";

    private Paint paint;
	private RectF rect;
	
	private int progress;
    private float angle;

    private float strokeWidth = 7;
    private int strokeColor = Color.BLACK;

    public Ring(Context context, AttributeSet attrs) {
		super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Ring, 0, 0);
        try {
            strokeColor = typedArray.getColor(R.styleable.Ring_ringStrokeColor, Color.BLACK);
            strokeWidth = typedArray.getDimension(R.styleable.Ring_ringStrokeWidth, strokeWidth);
        } finally {
            typedArray.recycle();
        }

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(strokeWidth);
		paint.setColor(strokeColor);

        rect = new RectF(0, 0, 0, 0);
		progress = 0;
	}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float offset = strokeWidth + 2;
        rect.set(offset, offset, getMeasuredWidth() - offset, getMeasuredHeight() - offset);
    }

    @Override
	protected void onDraw(Canvas canvas) {
        canvas.drawArc(rect, -90, angle, false, paint);
	}

	public int getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
        this.strokeColor = Color.parseColor(strokeColor);
		paint.setColor(this.strokeColor);
	}

    public void setStrokeColor(int color){
        paint.setColor(color);
        invalidate();
    }

    public void setStrokeWidth(int width){
        paint.setStrokeWidth(width);
        invalidate();
    }

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
        this.angle = -3.6f * progress;
	}

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
