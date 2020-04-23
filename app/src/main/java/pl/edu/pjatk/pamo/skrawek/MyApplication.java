package pl.edu.pjatk.pamo.skrawek;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class MyApplication extends Application {
    public ApplicationComponent appComponent = DaggerApplicationComponent.create();

    private static MyApplication mInstance;
    private static Context context;
    private static Resources res;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        res = getResources();
        context = getApplicationContext();
    }

    public static String getStringFromRes(int id) {
        return res.getString(id);
    }

    public static Context getAppContext() {
        return context;
    }
}
