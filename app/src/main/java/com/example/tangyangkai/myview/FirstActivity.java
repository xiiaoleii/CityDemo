package com.example.tangyangkai.myview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {


    private Button firstBtn, secondBtn, thirdBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, cityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }

    private void init() {

        firstBtn = (Button) findViewById(R.id.first_btn);
        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
            }
        });

        secondBtn = (Button) findViewById(R.id.second_btn);
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });
        thirdBtn = (Button) findViewById(R.id.third_btn);
        thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, ThirdActivity.class));
            }
        });


        fourBtn = (Button) findViewById(R.id.four_btn);
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, FourActivity.class));
            }
        });

        fiveBtn = (Button) findViewById(R.id.five_btn);
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, FiveActivity.class));

            }
        });


        sixBtn = (Button) findViewById(R.id.six_btn);
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, MyScrollViewActivity.class));
            }
        });

        sevenBtn = (Button) findViewById(R.id.seven_btn);
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SevenActivity.class));
            }
        });

        cityBtn = (Button) findViewById(R.id.city_btn);
        cityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, CityActivity.class));
            }
        });


    }
}
