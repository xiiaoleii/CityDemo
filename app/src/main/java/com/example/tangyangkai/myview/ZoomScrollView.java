package com.example.tangyangkai.myview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by tangyangkai on 16/6/16.
 */
public class ZoomScrollView extends ScrollView {

    private View mContentView;
    private Rect mRect;

    public ZoomScrollView(Context context) {
        this(context, null);
    }

    public ZoomScrollView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ZoomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化
    private void init() {
        mRect = new Rect();
    }


    /*
   onFinishInflate()函数,当View中所有的子控件均被映射成xml后触发,也就是布局解析结束后会调用的函数
   由于ScrollView只能有一个子布局,所以,我们通过getChildAt(0)直接获取ScrollView的子布局即可
    */
    @Override
    protected void onFinishInflate() {
        mContentView = getChildAt(0);
        super.onFinishInflate();

    }

    //对所有的childview进行定位,设置childview的绘制区域
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mContentView != null) {
            mRect.set(mContentView.getLeft(), mContentView.getTop(), mContentView.getRight(), mContentView.getBottom());
        }


    }
}
