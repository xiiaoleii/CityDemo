package com.example.tangyangkai.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SevenActivity extends AppCompatActivity {

    private MyColorView colorView;

    public static int[] sizes = new int[]{10, 15, 20, 25, 30};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        initview();
    }

    private void initview() {

        colorView = (MyColorView) findViewById(R.id.colorView);

    }
}
