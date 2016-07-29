package com.example.tangyangkai.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by tangyangkai on 16/5/25.
 */
public class MyScrollView extends View {


    private String bigText, smallText;
    private int textColor, lineColor;
    private int textSize;
    private Paint mPaint;
    private Rect mBound, mTxtBound;
    private int lineX, size, txtSize;
    private int mTouchSlop;
    private int state;
    private int xDown, xMove, yDown, yMove, dx;
    private OnScrollListener listener;
    private int oneSize, thirdSize, bigTxtSize;


    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们自定义的样式属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyScrollView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.MyScrollView_lineColor:
                    // 默认颜色设置为黑色
                    lineColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MyScrollView_titleColor:
                    textColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MyScrollView_titleSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    textSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }

        }
        array.recycle();
        init();
        //滑动到最小距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void init() {
        //初始化
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBound = new Rect();
        mTxtBound = new Rect();
        bigTxtSize = textSize;
        oneSize = textSize - 15;
        thirdSize = textSize - 15;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        txtSize = size - 2;
        bigText = String.valueOf(size);
        smallText = String.valueOf(txtSize);
        mPaint.setColor(lineColor);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint);
        lineX = getWidth() / 10;
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                mPaint.setTextSize(bigTxtSize);
                if (bigTxtSize == textSize - 15) {
                    mPaint.setColor(lineColor);
                    canvas.drawLine(lineX, 0, lineX, getHeight() / 5, mPaint);
                } else {
                    mPaint.setColor(textColor);
                    canvas.drawLine(lineX, 0, lineX, getHeight() / 3, mPaint);
                }
                mPaint.getTextBounds(bigText, 0, bigText.length(), mBound);
                canvas.drawText(bigText, lineX - mBound.width() / 2, getHeight() / 2 + mBound.height() * 3 / 4, mPaint);
            } else if (i == 0 || i == 4) {
                mPaint.setColor(lineColor);
                mPaint.setTextSize(textSize - 15);
                mPaint.getTextBounds(smallText, 0, smallText.length(), mTxtBound);
                canvas.drawLine(lineX, 0, lineX, getHeight() / 5, mPaint);
                canvas.drawText(String.valueOf(txtSize), lineX - mTxtBound.width() / 2, getHeight() / 2 + mTxtBound.height() * 3 / 4, mPaint);
            } else if (i == 1) {
                mPaint.setTextSize(oneSize);
                if (oneSize == textSize) {
                    mPaint.setColor(textColor);
                    canvas.drawLine(lineX, 0, lineX, getHeight() / 3, mPaint);
                } else {
                    mPaint.setColor(lineColor);
                    canvas.drawLine(lineX, 0, lineX, getHeight() / 5, mPaint);
                }
                mPaint.getTextBounds(smallText, 0, smallText.length(), mTxtBound);
                canvas.drawText(String.valueOf(txtSize), lineX - mTxtBound.width() / 2, getHeight() / 2 + mTxtBound.height() * 3 / 4, mPaint);
            } else {
                mPaint.setTextSize(thirdSize);
                if (thirdSize == textSize) {
                    mPaint.setColor(textColor);
                    canvas.drawLine(lineX, 0, lineX, getHeight() / 3, mPaint);

                } else {
                    mPaint.setColor(lineColor);
                    canvas.drawLine(lineX, 0, lineX, getHeight() / 5, mPaint);
                }
                mPaint.getTextBounds(smallText, 0, smallText.length(), mTxtBound);
                canvas.drawText(String.valueOf(txtSize), lineX - mTxtBound.width() / 2, getHeight() / 2 + mTxtBound.height() * 3 / 4, mPaint);
            }
            txtSize++;
            lineX += getWidth() / 5;
        }

    }

    public void setListener(OnScrollListener listener) {
        this.listener = listener;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                dx = xMove - xDown;
                int dy = yMove - yDown;
                //如果是从左向右滑动
                if (xMove > xDown && Math.abs(dx) > mTouchSlop * 2 && Math.abs(dy) < mTouchSlop) {
                    state = 1;
                }
                //如果是从右向左滑动
                if (xMove < xDown && Math.abs(dx) > mTouchSlop * 2 && Math.abs(dy) < mTouchSlop) {
                    state = 2;

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (state == 1 && bigTxtSize - oneSize > -15) {
                    bigTxtSize = bigTxtSize - 1;
                    oneSize = oneSize + 1;
                    postInvalidate();
                }
                if (state == 2 && bigTxtSize - thirdSize > -15) {
                    bigTxtSize = bigTxtSize - 1;
                    thirdSize = thirdSize + 1;
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (state == 1) {
                    size = size - 1;
                    bigTxtSize = textSize;
                    oneSize = textSize - 15;
                    postInvalidate();
                    listener.OnScroll(size);
                    state = 0;
                }
                if (state == 2) {
                    size = size + 1;
                    bigTxtSize = textSize;
                    thirdSize = textSize - 15;
                    postInvalidate();
                    listener.OnScroll(size);
                    state = 0;
                }
                break;
        }
        return true;
    }


    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
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
            width = widthSize*1/2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
          height=heightSize*1/9;
        }


        setMeasuredDimension(width, height);
    }

    public interface OnScrollListener {
        void OnScroll(int size);
    }


}
