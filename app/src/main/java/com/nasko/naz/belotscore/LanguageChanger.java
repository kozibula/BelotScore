package com.nasko.naz.belotscore;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.Locale;


public class LanguageChanger {

    private static String language;
    private final static String FILENAME = "CommonPrefs";

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String newLanguage) {
        language = newLanguage;
    }

    public static void changeLang(String lang, Context context) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang, context);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static void saveLocale(String lang, Context context) {
        String langPref = "Language";
        SharedPreferences prefs = context.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.apply();
    }

    public static void loadLocale(Context context) {
        String langPref = "Language";
        SharedPreferences prefs = context.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language, context);
    }


}
