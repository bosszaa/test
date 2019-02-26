package com.bossomdong.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    LinearLayout btnMeditation, btnManual, btnHolyday, btnTemple, btnPrayer, btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMeditation = findViewById(R.id.btn_meditation);
        btnManual = findViewById(R.id.btn_manual);
        btnHolyday = findViewById(R.id.btn_holyday);
        btnTemple = findViewById(R.id.btn_temple);
        btnPrayer = findViewById(R.id.btn_prayer);
        btnSetting = findViewById(R.id.btn_setting);

        btnMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageMeditation();
            }
        });
        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageManual();
            }
        });
        btnHolyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHolyday();
            }
        });
        btnPrayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPagePrayer();
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetting();
            }
        });
    }

    private void openPageMeditation() {
        Intent intent = new Intent(this, MeditationActivity.class);
        startActivity(intent);
    }

    private void openPageManual() {
        Intent intent = new Intent(this, ManualActivity.class);
        startActivity(intent);
    }

    private void openHolyday() {
        Intent intent = new Intent(this, HolydayActivity.class);
        startActivity(intent);
    }

    private void openPagePrayer() {
        Intent intent = new Intent(this, PrayerActivity.class);
        startActivity(intent);
    }

    private void openSetting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
