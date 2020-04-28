package pl.edu.pjatk.pamo.skrawek;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class MyApplication extends Application {
    public ApplicationComponent appComponent = DaggerApplicationComponent.create();

    private static SharedPreferences preferences;
    private static Resources res;

    @Override
    public void onCreate() {
        super.onCreate();
        res = getResources();
        preferences = getApplicationContext()
                .getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static String getStringFromRes(int id) {
        return res.getString(id);
    }

    public static void addProperty(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public static String getProperty(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }
}
