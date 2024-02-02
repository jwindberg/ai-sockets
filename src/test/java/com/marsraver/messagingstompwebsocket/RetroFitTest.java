package com.marsraver.messagingstompwebsocket;

import com.marsraver.messagingstompwebsocket.sonar.PingService;
import com.marsraver.messagingstompwebsocket.sonar.Sound;
import com.marsraver.messagingstompwebsocket.sonar.SoundService;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

public class RetroFitTest {

    @Test
    public void retroFit() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.7.214:8080")
                .build();

        SoundService service = retrofit.create(SoundService.class);

        Call<Void> call = service.playSound(Sound.SONAR_SWEEP);
        Response<Void> response = call.execute();
        if (response.isSuccessful()) {
            System.out.println(response);
        }
    }
}
