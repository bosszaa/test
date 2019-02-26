package com.bossomdong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SettingActivity";

    private RadioButton btnSea;
    private RadioButton btnForest;
    private RadioButton btnMan;
    private RadioButton btnWomen;

    private PrefsUtils prefsUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnSea = findViewById(R.id.btn_sea);
        btnForest = findViewById(R.id.btn_forest);
        btnMan = findViewById(R.id.btn_man);
        btnWomen = findViewById(R.id.btn_women);

        btnSea.setOnClickListener(this);
        btnForest.setOnClickListener(this);
        btnMan.setOnClickListener(this);
        btnWomen.setOnClickListener(this);

        prefsUtils = new PrefsUtils();

        updateView();
    }

    private void updateView() {
        switch (prefsUtils.getModel(this)) {
            case PrefsUtils.MODEL_MAN:
                btnMan.setChecked(true);
                break;
            case PrefsUtils.MODEL_WOMEN:
                btnWomen.setChecked(true);
                break;
            //TODO: sea and forest cases
        }
    }

    @Override
    public void onClick(View v) {
        RadioButton clickedBtn = (RadioButton)v;
        clickedBtn.setChecked(true);
        switch (clickedBtn.getId()) {
            case R.id.btn_sea:
                break;
            case R.id.btn_forest:
                break;
            case R.id.btn_man:
                prefsUtils.setModel(this, PrefsUtils.MODEL_MAN);
                Log.d(TAG, "onClick: man");
                break;
            case R.id.btn_women:
                prefsUtils.setModel(this, PrefsUtils.MODEL_WOMEN);
                Log.d(TAG, "onClick: women");
                break;
        }
        Log.d(TAG, "onClick: " + prefsUtils.getModel(this));
    }
}
