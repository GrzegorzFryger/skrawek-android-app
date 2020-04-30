package pl.edu.pjatk.pamo.skrawek.rest.auth;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.MyApplication;

import static pl.edu.pjatk.pamo.skrawek.MyApplication.addProperty;

/**
 * This class is responsible for storing globally most important application params - for instance user email
 */
public class SessionManager {
    public static final String USER_TOKEN = "token";
    public static final String GUARDIAN_ID = "guardianId";
    public static final String EMAIL = "email";


    private SessionManager() {
    }

    public static void saveAuthToken(String token) {
        addProperty(USER_TOKEN, token);
    }

    public static String getAuthToken() {
        return MyApplication.getProperty(USER_TOKEN, "");
    }

    public static void saveEmail(String email) {
        addProperty(EMAIL, email);
    }

    public static String getEmail() {
        return MyApplication.getProperty(EMAIL, "");
    }

    public static void saveGuardianId(UUID guardianId) {
        addProperty(GUARDIAN_ID, guardianId.toString());
    }

    public static UUID getGuardianId() {
        return UUID.fromString(MyApplication.getProperty(GUARDIAN_ID, ""));
    }
}
