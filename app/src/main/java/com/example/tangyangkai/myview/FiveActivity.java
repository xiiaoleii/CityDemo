package com.example.tangyangkai.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FiveActivity extends AppCompatActivity {

    private MyBesselView besselView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        initview();
    }

    private void initview() {

        besselView = (MyBesselView) findViewById(R.id.my_besselview);

    }
}
