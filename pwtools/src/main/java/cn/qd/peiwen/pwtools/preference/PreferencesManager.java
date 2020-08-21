package cn.qd.peiwen.pwtools.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class PreferencesManager {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public PreferencesManager(Context context) {
        this.context = context;
    }

    public void init(String filename, int model) {
        this.preferences = context.getSharedPreferences(filename, model);
        this.editor = this.preferences.edit();
    }

    public void clear() {
        this.editor.clear().commit();
    }

    public boolean isEmpty() {
        return (0 == this.preferences.getAll().size());
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public void remove(String key) {
        this.editor.remove(key).commit();
    }

    public boolean contains(String key) {
        return this.preferences.contains(key);
    }

    public void putInt(String key, int value) {
        this.editor.putInt(key, value).commit();
    }

    public int getInt(String key) {
        return this.preferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return this.preferences.getInt(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        this.editor.putFloat(key, value).commit();
    }

    public float getFloat(String key) {
        return this.preferences.getFloat(key, 0.f);
    }

    public float getFloat(String key, float defaultValue) {
        return this.preferences.getFloat(key, defaultValue);
    }

    public void putLong(String key, long value) {
        this.editor.putLong(key, value).commit();
    }

    public long getLong(String key) {
        return this.preferences.getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue) {
        return this.preferences.getLong(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        this.editor.putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key) {
        return this.preferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return this.preferences.getBoolean(key, defaultValue);
    }

    public void putString(String key, String value) {
        this.editor.putString(key, value).commit();
    }

    public String getString(String key) {
        return this.preferences.getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return this.preferences.getString(key, defaultValue);
    }

    public void putStringSet(String key, Set<String> value) {
        this.editor.putStringSet(key, value).commit();
    }

    public Set<String> getStringSet(String key) {
        return this.preferences.getStringSet(key, null);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return this.preferences.getStringSet(key, defaultValue);
    }
}
