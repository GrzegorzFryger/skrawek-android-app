package pl.edu.pjatk.pamo.skrawek.rest.auth;

import static pl.edu.pjatk.pamo.skrawek.MyApplication.addProperty;
import static pl.edu.pjatk.pamo.skrawek.MyApplication.getProperty;

public class SessionManager {
    private static final String USER_TOKEN = "user_token";

    public SessionManager() {
    }

    public void saveAuthToken(String token) {
        addProperty(USER_TOKEN, token);
    }

    public String getAuthToken() {
        return getProperty(USER_TOKEN, "");
    }
}
