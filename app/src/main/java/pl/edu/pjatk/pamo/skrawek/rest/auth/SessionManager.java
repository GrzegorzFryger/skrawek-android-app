package pl.edu.pjatk.pamo.skrawek.rest.auth;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;

import static pl.edu.pjatk.pamo.skrawek.MyApplication.addProperty;

public class SessionManager {
    public static final String USER_TOKEN = "token";

    public static final String GUARDIAN_ID = "guardianId";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String POSTAL_CODE = "postalCode";
    public static final String CITY = "city";
    public static final String STREET_NUMBER = "streetNumber";
    public static final String PHONE = "phone";
    public static final String STATUS = "status";
    public static final String EMAIL = "email";


    private SessionManager() {
    }

    public static void saveAuthToken(String token) {
        addProperty(USER_TOKEN, token);
    }

    public static String getAuthToken() {
        return MyApplication.getProperty(USER_TOKEN, "");
    }

    public static void saveAccountData(Account account) {
        addProperty(GUARDIAN_ID, account.getId().toString());
        addProperty(NAME, account.getName());
        addProperty(SURNAME, account.getSurname());
        addProperty(POSTAL_CODE, account.getPostalCode());
        addProperty(CITY, account.getCity());
        addProperty(STREET_NUMBER, account.getStreetNumber());
        addProperty(PHONE, account.getPhone());
        addProperty(STATUS, account.getStatus().name());
        addProperty(EMAIL, account.getEmail());
    }

    public static String getProperty(String name) {
        return MyApplication.getProperty(name, "");
    }

    public static UUID getGuardianId() {
        return UUID.fromString(getProperty(GUARDIAN_ID));
    }
}
