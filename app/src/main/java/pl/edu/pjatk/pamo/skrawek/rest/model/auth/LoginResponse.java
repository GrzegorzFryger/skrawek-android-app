package pl.edu.pjatk.pamo.skrawek.rest.model.auth;

/**
 * Model class - used when calling REST API
 */
public class LoginResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
