package com.example.template_dpr_now.Slide_Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SlideManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "welcome-slide";
    private static final String IS_FIRST_TIME = "isFirstTime";

    public SlideManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTime(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTime(){
        return pref.getBoolean(IS_FIRST_TIME, true);
    }
}
