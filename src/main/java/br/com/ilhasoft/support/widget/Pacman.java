package br.com.ilhasoft.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorRes;

import br.com.ilhasoft.support.R;

public class Pacman extends View {

    private static final int MAX_ANGLE = 360;

    private Paint backgroundPaint;
    private Paint foregroundPaint;

	private RectF rect;

	private int progress;
    private float angle;

    private int backgroundColor = Color.BLACK;
    private int foregroundColor = Color.WHITE;

    public Pacman(Context context, AttributeSet attrs) {
		super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Pacman, 0, 0);
        try {
            backgroundColor = typedArray.getColor(R.styleable.Pacman_pacmanBackgroundColor, Color.BLACK);
            foregroundColor = typedArray.getColor(R.styleable.Pacman_pacmanForegroundColor, Color.WHITE);
            progress = typedArray.getInteger(R.styleable.Pacman_pacmanProgress, 0);
        } finally {
            typedArray.recycle();
        }

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(backgroundColor);

		foregroundPaint = new Paint();
		foregroundPaint.setAntiAlias(true);
        foregroundPaint.setStrokeWidth(10);
		foregroundPaint.setStyle(Paint.Style.FILL);
		foregroundPaint.setColor(foregroundColor);

        rect = new RectF(0, 0, 0, 0);
		setProgress(progress);
	}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
	protected void onDraw(Canvas canvas) {
        canvas.drawOval(rect, backgroundPaint);
        canvas.drawArc(rect, 135, angle, true, foregroundPaint);
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = Color.parseColor(backgroundColor);
		backgroundPaint.setColor(this.backgroundColor);
	}

    @Override
    public void setBackgroundColor(@ColorRes int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(@ColorRes int foregroundColor) {
        this.foregroundColor = foregroundColor;
        foregroundPaint.setColor(getResources().getColor(foregroundColor));
    }

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
        this.angle = 3.6f * progress;
	}

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
