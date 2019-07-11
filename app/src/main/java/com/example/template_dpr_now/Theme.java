package com.example.template_dpr_now;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Theme {
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    public static void createTheme(Context context){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        int theme = pref.getInt("theme_app", 0);

        switch (theme){
            case 0:
                context.setTheme(R.style.DefaultTheme);
                break;

            case 1:
                context.setTheme(R.style.DarkTheme);
                break;
            case 2:
                context.setTheme(R.style.Green_goblin);
        }
    }

    public static void setTheme(Context context, int theme){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
        editor.putInt("theme_app", theme);
        editor.commit();
    }
}
