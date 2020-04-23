package pl.edu.pjatk.pamo.skrawek.rest;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthInterceptor;
import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;
import pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager;
import pl.edu.pjatk.pamo.skrawek.rest.config.UnsafeOkHttpClient;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.BASE_URL;

@Module
public class NetworkModule {
    private final OkHttpClient okHttpClient = initializeOkHttpClient();
    private final Retrofit retrofit = buildRetrofit();

    @Provides
    public FinancesService financesService() {
        return retrofit.create(FinancesService.class);
    }

    @Provides
    public AuthService authService() {
        return retrofit.create(AuthService.class);
    }

    @Provides
    public SessionManager sessionManager() {
        return new SessionManager();
    }

    private AuthInterceptor authInterceptor() {
        return new AuthInterceptor(sessionManager());
    }

    private OkHttpClient initializeOkHttpClient() {
        OkHttpClient httpClient = new UnsafeOkHttpClient().getUnsafeOkHttpClient(authInterceptor());
        return httpClient;
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
