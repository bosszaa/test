package com.bossomdong.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MeditationActivity extends AppCompatActivity {

    LinearLayout one,two,tree,four,five,six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        one = findViewById(R.id.btn_one);
        two = findViewById(R.id.btn_two);
        tree = findViewById(R.id.btn_tree);
        four = findViewById(R.id.btn_four);
        five = findViewById(R.id.btn_five);
        six = findViewById(R.id.btn_six);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage1();
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage2();
            }
        });

        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage3();
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage4();
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage5();
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage6();
            }
        });

    }

    private void openNewPage1(){
        Intent intent = new Intent(this,Page1.class);
        startActivity(intent);

    }
    private void openNewPage2(){
        Intent intent = new Intent(this,Page2.class);
        startActivity(intent);

    }
    private void openNewPage3() {
        Intent intent = new Intent(this, Page3.class);
        startActivity(intent);
    }
    private void openNewPage4() {
        Intent intent = new Intent(this, Page4.class);
        startActivity(intent);
    }
    private void openNewPage5() {
        Intent intent = new Intent(this, Page5.class);
        startActivity(intent);
    }
    private void openNewPage6() {
        Intent intent = new Intent(this, Page6.class);
        startActivity(intent);
    }

}
