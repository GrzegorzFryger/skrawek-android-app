package pl.edu.pjatk.pamo.skrawek;

import android.app.Application;
import android.content.res.Resources;

public class MyApplication extends Application {
    ApplicationComponent appComponent = DaggerApplicationComponent.create();

    private static MyApplication mInstance;
    private static Resources res;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        res = getResources();
    }

    public static String getStringFromRes(int id) {
        return res.getString(id);
    }
}
