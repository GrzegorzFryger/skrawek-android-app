package pl.edu.pjatk.pamo.skrawek.rest.config;

import org.junit.Test;

import okhttp3.OkHttpClient;

import static org.junit.Assert.assertNotNull;

public class UnsafeOkHttpClientTest {

    @Test
    public void Should_CreateOkHttpClient() {
        //Given

        //When
        OkHttpClient result = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        //Then
        assertNotNull(result);
    }
}