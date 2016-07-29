package com.example.tangyangkai.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyScrollViewActivity extends AppCompatActivity {


    private ZoomScrollView zoomScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scroll_view);
        initviews();
    }

    private void initviews() {
        zoomScrollView = (ZoomScrollView) findViewById(R.id.zoom_scrollview);
    }
}
