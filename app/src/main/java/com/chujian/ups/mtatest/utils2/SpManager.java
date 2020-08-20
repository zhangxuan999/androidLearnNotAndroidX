package com.chujian.ups.mtatest.utils2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



public class SpManager {

    public static void save(String key, String value) {
        Editor editor = getEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public static void save(String key, int value) {
        Editor editor = getEditor();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void save(String key, long value) {
        Editor editor = getEditor();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void save(String key, boolean value) {
        Editor editor = getEditor();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void remove(String key) {
        Editor editor = getEditor();
        editor.remove(key);
        editor.commit();
    }

    public static boolean containKey(String key) {
        SharedPreferences sp = getSharePreference();
        return sp.contains(key);
    }

    public static String get(String key) {
        SharedPreferences sp = getSharePreference();
        return sp.getString(key, "");
    }

    public static String get(String key, String def) {
        SharedPreferences sp = getSharePreference();
        return sp.getString(key, def);
    }

    public static boolean getBoolean(String key) {
        SharedPreferences sp = getSharePreference();
        return sp.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean def) {
        SharedPreferences sp = getSharePreference();
        return sp.getBoolean(key, def);
    }

    public static int getInt(String key) {
        SharedPreferences sp = getSharePreference();
        return sp.getInt(key, -1);
    }

    public static long getLong(String key) {
        SharedPreferences sp = getSharePreference();
        return sp.getLong(key, -1L);
    }

    static Context context;
    private static SharedPreferences getSharePreference() {
        return context.getSharedPreferences("chujian_jh_sdk_config", Context.MODE_PRIVATE);
    }

    private static Editor getEditor() {
        SharedPreferences sp = getSharePreference();
        return sp.edit();
    }
}
