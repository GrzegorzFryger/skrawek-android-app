package pl.edu.pjatk.pamo.skrawek.rest.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import pl.edu.pjatk.pamo.skrawek.R;

public class SessionManager {
    private static final String USER_TOKEN = "user_token";

    private final SharedPreferences preferences;

    public SessionManager(Context context) {
        this.preferences = context.getSharedPreferences(
                context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token) {
        Editor editor = preferences.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String getAuthToken() {
        return preferences.getString(USER_TOKEN, "");
    }
}
