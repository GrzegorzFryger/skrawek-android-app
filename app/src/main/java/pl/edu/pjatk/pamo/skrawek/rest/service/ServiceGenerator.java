package pl.edu.pjatk.pamo.skrawek.rest.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.BASE_URL;
import static pl.edu.pjatk.pamo.skrawek.rest.config.UnsafeOkHttpClient.getUnsafeOkHttpClient;

public class ServiceGenerator {
    private static OkHttpClient httpClient = getUnsafeOkHttpClient();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
