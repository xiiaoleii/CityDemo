package com.example.tangyangkai.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements MyScrollView.OnScrollListener {

    private MyScrollView scrollView;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initview();
    }

    private void initview() {
        scrollView = (MyScrollView) findViewById(R.id.scroll_view);
        scrollView.setSize(1992);
        scrollView.setListener(this);
        txt = (TextView) findViewById(R.id.year_txt);
        txt.setText("出生年份" + scrollView.getSize() + " (年)");
    }

    @Override
    public void OnScroll(int size) {
        txt.setText("出生年份" + size + " (年)");
    }
}