package pl.edu.pjatk.pamo.skrawek.rest.config;

/**
 * This class contains constants, that are used when calling REST API services
 */
public class RequestMappings {
    /**
     * Android Virtual Devices are listening for 10.0.2.2
     * and forwarding all the requests to your computer
     */
    public static final String BASE_URL = "https://192.168.1.130:8080/";

    /**
     * Base URL for controllers related to <strong>AUTH mechanism</strong>
     */
    public static final String API_AUTH = "/api/authenticate";

    /**
     * Base URL for controllers from REST API <strong>finances</strong> module
     */
    public static final String API_FINANCES = "/api/finances/";

    /**
     * Base URL for controllers from REST API <strong>Account</strong> module
     */
    public static final String API_ACCOUNT = "/api/account/";

    /**
     * Base URL for controllers from REST API <strong>Receivables</strong> module
     */
    public static final String API_RECEIVABLES = "/api/receivables/";

    /**
     * Base URL for controllers from REST API <strong>Calendar</strong> module
     */
    public static final String API_CALENDAR = "/api/calendar/";
}
