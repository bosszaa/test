package com.bossomdong.myapplication;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefsUtils {
    public static final int MODEL_MAN = 0;
    public static final int MODEL_WOMEN = 1;

    private final String PREF_MODEL = "MODEL";
    private final String PREF_BACKGROUND = "BACKGROUND";

    //read
    private SharedPreferences getPrefs(Context context, String prefName) {
        return context.getSharedPreferences(prefName,0);
    }

    //write
    private SharedPreferences.Editor getEditor(Context context, String prefName) {
        return getPrefs(context, prefName).edit();
    }

    //model
    public void setModel(Context context, int model) {
        getEditor(context, PREF_MODEL).putInt(PREF_MODEL, model).apply();
    }

    public int getModel(Context context) {
        return getPrefs(context, PREF_MODEL).getInt(PREF_MODEL, 0);
    }

    //TODO: add background

}
