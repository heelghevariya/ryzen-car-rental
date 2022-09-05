package com.example.ryzencarrent.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    public static final String KEY_PREFS_EMAIL = "email";
    public static final String KEY_PREFS_UID = "uid";
    public static final String KEY_PREFS_USERNAME = "username";
    public static final String KEY_PREFS_ISLOGGED = "islogged";
    public static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getemailpref() {
        return _sharedPrefs.getString(KEY_PREFS_EMAIL, "");
    }

    public void saveemailpref(String email) {
        _prefsEditor.putString(KEY_PREFS_EMAIL, email);
        _prefsEditor.commit();
    }

    public int getuidpref() {
        return _sharedPrefs.getInt(KEY_PREFS_UID, 0);
    }

    public void saveuidpref(int uid) {
        _prefsEditor.putInt(KEY_PREFS_UID, uid);
        _prefsEditor.commit();
    }

    public String getunamepref() {
        return _sharedPrefs.getString(KEY_PREFS_USERNAME, "");
    }

    public void saveunamepref(String uname) {
        _prefsEditor.putString(KEY_PREFS_USERNAME, uname);
        _prefsEditor.commit();
    }

    public boolean isloggedin() {
        return _sharedPrefs.getBoolean(KEY_PREFS_ISLOGGED, false);
    }

    public void setlogged(boolean logged) {
        _prefsEditor.putBoolean(KEY_PREFS_ISLOGGED, logged);
        _prefsEditor.commit();
    }


}
