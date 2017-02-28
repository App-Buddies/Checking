package interviewmaster.admin.interview.com.checking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://private-2a004-androidtest3.apiary-mock.com";


    public static Retrofit getClient() {
        Retrofit builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return builder;
    }

    public static Endpoint getApiService() {
        return getClient().create(Endpoint.class);
    }
}
