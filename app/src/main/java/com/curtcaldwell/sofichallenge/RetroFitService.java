package com.curtcaldwell.sofichallenge;

import com.curtcaldwell.sofichallenge.model.PicsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetroFitService {

    private PicApi picApi;

    private static final String BASE_URL = "https://api.imgur.com/3/gallery/search/";

    RetroFitService() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        picApi = retrofit.create(PicApi.class);
    }

    Call<PicsResponse> getPictureData(String input) {
        return picApi.getPicsResponse(1, input);

    }
}


