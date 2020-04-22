package pl.edu.pjatk.pamo.skrawek.rest;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.edu.pjatk.pamo.skrawek.rest.RequestMappings.BASE_URL;

@Module
public class NetworkModule {

    @Provides
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
