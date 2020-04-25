package pl.edu.pjatk.pamo.skrawek.rest.auth;

import static pl.edu.pjatk.pamo.skrawek.MyApplication.addProperty;
import static pl.edu.pjatk.pamo.skrawek.MyApplication.getProperty;

public class SessionManager {
    private static final String USER_TOKEN = "user_token";

    private SessionManager() {
    }

    public static void saveAuthToken(String token) {
        addProperty(USER_TOKEN, token);
    }

    public static String getAuthToken() {
        return getProperty(USER_TOKEN, "");
    }
}
