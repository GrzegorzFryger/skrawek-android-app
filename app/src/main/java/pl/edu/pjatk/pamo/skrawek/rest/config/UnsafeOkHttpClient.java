package pl.edu.pjatk.pamo.skrawek.rest.config;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthInterceptor;
import pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager;

/**
 * This is initial solution - for now we will ignore security concerns and accept all SSL certificates
 * TODO: Find more secure solution in future releases
 */
public class UnsafeOkHttpClient {
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = createTrustManager();

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            addInterceptors(builder);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void addInterceptors(OkHttpClient.Builder builder) {
        AuthInterceptor authInterceptor = new AuthInterceptor();
        builder.addInterceptor(authInterceptor);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(logging);
    }

    private static TrustManager[] createTrustManager() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                   String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                   String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }
}
