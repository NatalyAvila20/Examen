package com.example.examen;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static final String BASE_URL = "https://mindicador.cl/api/";
    private static NetworkService instance;
    private Retrofit retrofit;

    private NetworkService() {
          retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    public ExchangeRateService getExchangeRateService() {
        return retrofit.create(ExchangeRateService.class);
    }
}

