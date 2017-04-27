package com.yan.base.application;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * 缓存的全局变量 记得commit(); 性能优化，多个保存的时候能够减少事物的提交次数
 */
public class GlobalPreference {
    /**
     * 全局的preference实例
     */
    private static volatile GlobalPreference gPrefs;
    /**
     * 上下文，推荐使用application上下文
     */
    private Context context;
    /**
     * SharedPreferences实例
     */
    private SharedPreferences prefs;
    /**
     * SharedPreferences.Editor
     */
    private SharedPreferences.Editor editor;

    public static synchronized GlobalPreference getInstance() {
        return gPrefs;
    }

    public static void init(Context context, String prefsName, int mode) {
        gPrefs = new GlobalPreference();
        gPrefs.context = context;
        gPrefs.prefs = gPrefs.context.getSharedPreferences(prefsName, mode);
        gPrefs.editor = gPrefs.prefs.edit();
    }

    private GlobalPreference() {
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.prefs.getBoolean(key, defaultVal);
    }

    public boolean getBoolean(String key) {
        return this.prefs.getBoolean(key, false);
    }

    public String getString(String key, String defaultVal) {
        return this.prefs.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.prefs.getString(key, (String) null);
    }

    public int getInt(String key, int defaultVal) {
        return this.prefs.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }

    public float getFloat(String key, float defaultVal) {
        return this.prefs.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.prefs.getFloat(key, 0.0F);
    }

    public long getLong(String key, long defaultVal) {
        return this.prefs.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.prefs.getLong(key, 0L);
    }

    public Set<String> getStringSet(String key, Set<String> defaultVal) {
        return this.prefs.getStringSet(key, defaultVal);
    }

    public Set<String> getStringSet(String key) {
        return this.prefs.getStringSet(key, (Set) null);
    }

    public Map<String, ?> getAll() {
        return this.prefs.getAll();
    }


    //put
    public GlobalPreference putString(String key, String value) {
        this.editor.putString(key, value);
        return this;
    }

    public GlobalPreference putInt(String key, int value) {
        this.editor.putInt(key, value);
        return this;
    }

    public GlobalPreference putFloat(String key, float value) {
        this.editor.putFloat(key, value);
        return this;
    }

    public GlobalPreference putLong(String key, long value) {
        this.editor.putLong(key, value);
        return this;
    }

    public GlobalPreference putBoolean(String key, boolean value) {
        this.editor.putBoolean(key, value);
        return this;
    }

    public GlobalPreference putStringSet(String key, Set<String> value) {
        this.editor.putStringSet(key, value);
        this.editor.commit();
        return this;
    }

    /**
     * 提交当前事物
     */
    public void commit() {
        this.editor.commit();
    }
}
