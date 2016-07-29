package com.example.tangyangkai.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tangyangkai on 16/7/20.
 */
public class MyColorView extends View {

    private Paint paint;
    private int mWidth, mHeight;

    private double rectLeft, rectRight;

    public MyColorView(Context context) {
        this(context, null);

    }

    public MyColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);


        for (int i = 0; i < 5; i++) {


            Double right = Double.valueOf(SevenActivity.sizes[i]) / Double.valueOf(100);
            if (i == 0) {
                paint.setColor(getResources().getColor(R.color.error_color));
                canvas.drawRect((float) rectLeft, 0, (float) rectRight, mHeight, paint);
            } else if (i == 1) {
                paint.setColor(getResources().getColor(R.color.colorPrimary));
                canvas.drawRect((float) rectLeft, 0, (float) (right * mWidth + rectLeft), mHeight, paint);
            } else if (i == 2) {
                paint.setColor(getResources().getColor(R.color.error_color));
                canvas.drawRect((float) rectLeft, 0, (float) (right * mWidth + rectLeft), mHeight, paint);
            } else if (i == 3) {
                paint.setColor(getResources().getColor(R.color.colorPrimary));
                canvas.drawRect((float) rectLeft, 0, (float) (right * mWidth + rectLeft), mHeight, paint);
            } else {
                paint.setColor(getResources().getColor(R.color.error_color));
                canvas.drawRect((float) rectLeft, 0, (float) (right * mWidth + rectLeft), mHeight, paint);
            }

            Double left = Double.valueOf(SevenActivity.sizes[i]) / Double.valueOf(100);
            rectLeft += left * mWidth;
        }


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = widthSize * 1 / 2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = heightSize * 1 / 9;
        }
        mWidth = width;
        mHeight = height;
        Double x = Double.valueOf(SevenActivity.sizes[0]) / Double.valueOf(100);
        rectRight = x * mWidth;
        rectLeft = 0;
        setMeasuredDimension(width, height);
    }
}
