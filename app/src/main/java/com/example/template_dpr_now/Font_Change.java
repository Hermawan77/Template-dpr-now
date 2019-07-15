package com.example.template_dpr_now;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Font_Change {
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    public static void createFont (Context context){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        int font = pref.getInt("font", 0);

        switch (font){
            case 0:
                context.setTheme(R.style.Arial);
                break;
            case 1:
                context.setTheme(R.style.Times);
                break;
        }
    }

    public static void setFont (Context context, int font){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
        editor.putInt("font", font);
        editor.commit();
    }
}
