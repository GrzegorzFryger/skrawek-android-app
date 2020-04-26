package pl.edu.pjatk.pamo.skrawek.rest.auth;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import okhttp3.Interceptor;

import static org.mockito.MockitoAnnotations.initMocks;

public class AuthInterceptorTest {

    @Mock
    private Interceptor.Chain chain;

    private AuthInterceptor authInterceptor;

    @Before
    public void setUp() {
        initMocks(this);

        authInterceptor = new AuthInterceptor();
    }

    @Test(expected = NullPointerException.class)
    public void Should_ThrowException_When_InputIsNull() throws IOException {
        //Given

        //When
        authInterceptor.intercept(null);

        //Then
    }
}