package com.example.tangyangkai.myview;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AnimationSet;

/**
 * Created by tangyangkai on 16/5/30.
 */
public class MyTestView extends View {


    private Paint mPaint;
    private Path weavPath;
    //宽高
    private int mWidth;
    private int mHeight;
    private int xDown, xMove, yDown, yMove, dx, dy;
    private int mTouchSlop;

    //水波纹的宽高
    private float x, y;
    private AnimatorSet animSet;

    public MyTestView(Context context) {
        this(context, null);
    }

    public MyTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们自定义的样式属性
        init();
        //滑动到最小距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.textcolor));

        weavPath = new Path();
        animSet = new AnimatorSet();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制底部波纹
        weavPath.reset();
        weavPath.moveTo(0, mHeight);
        weavPath.lineTo(0, mHeight * 10 / 12);
        weavPath.cubicTo(x, y, mWidth * 5 / 10, mHeight * 11 / 12, mWidth, mHeight * 9 / 12);
        weavPath.lineTo(mWidth, mHeight);
        weavPath.lineTo(0, mHeight);
        canvas.drawPath(weavPath, mPaint);
    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        int action = ev.getAction();
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                xDown = x;
//                yDown = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                xMove = x;
//                yMove = y;
//                dx = xMove - xDown;
//                dy = yMove - yDown;
//
//                float scaleX = getScrollX();
//                Log.e("tag", scaleX + "@@" + dx);
//
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return true;
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        //如果布局里面设置的是固定值,这里取布局里面的固定值;如果设置的是match_parent,则取父布局的大小
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {

            //如果布局里面没有设置固定值,这里取布局的宽度的1/2
            width = widthSize * 1 / 2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            //如果布局里面没有设置固定值,这里取布局的高度的3/4
            height = heightSize * 3 / 4;
        }
        mWidth = width;
        mHeight = height;
        setMeasuredDimension(width, height);
        startAnim();

    }

    private void startAnim() {
        //圆弧动画的实现
        ValueAnimator xAnimator = ValueAnimator.ofFloat(mWidth * 1 / 10, mWidth * 3 / 10);
        xAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        ValueAnimator yAnimator = ValueAnimator.ofFloat(mHeight * 8 / 12, mHeight * 11 / 12);
        yAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animSet.setDuration(4000);
        animSet.playTogether(xAnimator, yAnimator);
        animSet.start();
    }


}
