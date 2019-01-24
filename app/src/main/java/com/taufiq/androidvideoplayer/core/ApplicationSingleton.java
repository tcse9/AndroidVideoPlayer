package com.taufiq.androidvideoplayer.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.taufiq.androidvideoplayer.BuildConfig;
import com.taufiq.androidvideoplayer.components.BaseComponents;;
import com.taufiq.androidvideoplayer.components.DaggerBaseComponents;
import com.taufiq.androidvideoplayer.modules.VideoModule;


public class ApplicationSingleton extends Application {

    private static ApplicationSingleton sInstance;
    private SharedPreferences mPref;
    private BaseComponents baseComponents;

    public static ApplicationSingleton getInstance() {
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initializeInstance();
    }


    /**
     * Initializes the singletone instance with other singletones.
     */
    private void initializeInstance() {

        //initializing the application preference file with MODE_PRIVATE
        mPref = this.getApplicationContext().getSharedPreferences(BuildConfig.APPLICATION_ID + "_pref", MODE_PRIVATE);
        baseComponents = DaggerBaseComponents.builder().videoModule(new VideoModule()).build();
    }

    /**
     * Gets the preference instance
     * @return
     */
    public SharedPreferences getSharedPreference() {
        return mPref;
    }

    /**
     * Saves a {@link String} in app's preference
     * @param key
     * @param value
     */
    public void savePrefString(String key, String value){
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Gets a {@link String} value from app's preference
     * @param key
     * @return
     */
    public String getPrefString(String key){
        return mPref.getString(key, "");
    }

    /**
     * Saves a boolean in app's preference
     * @param key
     * @param value
     */
    public void savePrefBoolean(String key, boolean value){
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Gets a boolean from app's preference
     * @param key
     * @return
     */
    public boolean getPrefBoolean(String key){
        return mPref.getBoolean(key, false);
    }

    /**
     * Saves an integer value in app's preference
     * @param key
     * @param value
     */
    public void savePrefInt(String key, int value){
        SharedPreferences.Editor editor = mPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Gets an integer value from app's preference
     * @param key
     * @return
     */
    public int getPrefInt(String key){
        return mPref.getInt(key, -1);
    }


    /**
     * Gets the DI instance
     * @return
     */
    public BaseComponents getBaseComponents() {
        return baseComponents;
    }


}
