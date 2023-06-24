package com.app.quranilham.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiQuran {
    private static String BASE_URL = "https://api.quran.com/api/v4/";
    private static Retrofit retrofit;
    public static ApiEndpoint endpoint () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndpoint.class);

}
}
