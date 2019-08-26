package com.curtcaldwell.sofichallenge;

import com.curtcaldwell.sofichallenge.model.PicsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PicApi {

    @Headers({
            "Authorization: Client-ID 126701cd8332f32"
    })

    @GET("{page}")
    Call<PicsResponse> getPicsResponse(@Path("page") Integer page, @Query("q") String q);


}
