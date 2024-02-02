package com.marsraver.messagingstompwebsocket.sonar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SoundService {

    @GET("sound/play")
    Call<Void> playSound(@Query("sound") Sound sound);
}
