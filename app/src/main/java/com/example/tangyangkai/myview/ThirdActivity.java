package com.example.tangyangkai.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private MyTestView testView;

    private int xDown, xMove, yDown, yMove, dx, dy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initview();
    }

    private void initview() {
        testView = (MyTestView) findViewById(R.id.mytestview);




    }




    @Override
    public boolean onTouchEvent(MotionEvent ev) {

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
                dy = yMove - yDown;

                float scaleX = testView.getScrollX();

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
