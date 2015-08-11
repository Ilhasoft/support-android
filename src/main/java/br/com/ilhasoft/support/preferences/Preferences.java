package br.com.ilhasoft.support.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Preferences {

    public static final String PREFS_NAME = "Preferences";

    private SharedPreferences preferences;
    private final String prefix;

    public Preferences(Context context, String prefix) {
        this.prefix = prefix;
        preferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_MULTI_PROCESS);
    }

    public Preferences(Context context) {
        this.prefix = "";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    synchronized public<T> T getValue(Enum attr, T defaultValue) {
        return getValue(attr.toString(), defaultValue);
    }
	
	@SuppressWarnings("unchecked")
    synchronized public<T> T getValue(String key, T defaultValue) {
        key = prefix + key;
		T resultValue = null;

		if(defaultValue instanceof Boolean) {
			resultValue = (T) Boolean.valueOf(preferences.getBoolean(key, (Boolean) defaultValue));
		} else if(defaultValue instanceof Float) {
			resultValue = (T) Float.valueOf(preferences.getFloat(key, (Float) defaultValue));
		} else if(defaultValue instanceof Integer) {
			resultValue = (T) Integer.valueOf(preferences.getInt(key, (Integer) defaultValue));
		} else if(defaultValue instanceof Long) {
			resultValue = (T) Long.valueOf(preferences.getLong(key, (Long) defaultValue));
		} else if(defaultValue instanceof String) {
			resultValue = (T) preferences.getString(key, (String)defaultValue);
		}

		return resultValue;
	}

    synchronized public<T> void setValue(Enum attr, T value) {
        setValue(attr.toString(), value);
    }

    synchronized public<T> void setValue(String key, T value) {
        key = prefix + key;
		Editor editor = preferences.edit();

		if(value instanceof Boolean) {
			editor.putBoolean(key, (Boolean)value);
		} else if(value instanceof Float) {
			editor.putFloat(key, (Float)value);
		} else if(value instanceof Integer) {
			editor.putInt(key, (Integer)value);
		} else if(value instanceof Long) {
			editor.putLong(key, (Long)value);
		} else if(value instanceof String) {
			editor.putString(key, (String) value);
		}

		editor.commit();
	}

}
