package com.correct.score.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient myClient;
    private Retrofit retrofit;

    private RetrofitClient() {



        retrofit = new Retrofit.Builder()
                .baseUrl(Constantine.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public static synchronized RetrofitClient getInstance() {

        if (myClient == null) {
            myClient = new RetrofitClient();
        }
        return myClient;

    }

    public APIService getApi() {

        return retrofit.create(APIService.class);
    }


}
