package com.marsraver.messagingstompwebsocket.sonar;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
@RestController
@AllArgsConstructor
@RequestMapping("webhook")
public class PingController {

    private PingService pingService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Registration registration) {
        return ResponseEntity.ok(
                pingService.register(registration.hostName, "http://" + registration.hostName + ":8080/sound/play", registration.getSound()));
    }

    @DeleteMapping("{hostName}")
    public ResponseEntity<?> delete(@PathVariable String hostName) {
        return ResponseEntity.ok(
                pingService.unRegister(hostName));
    }

    @GetMapping
    public ResponseEntity<Collection<?>> getMembers() {
        return ResponseEntity.ok(
                pingService.getMembers());
    }

    @GetMapping("play")
    public ResponseEntity<Void> play() {
        pingService.playSound();
        return ResponseEntity.ok(null);
    }

    @GetMapping("playOneAtAtTime")
    public ResponseEntity<Void> playOneAtAtTime() {
        pingService.playOneAtAtTime();
        return ResponseEntity.ok(null);
    }

    @Data
    public static class Registration {
        private String hostName;
        private Sound sound = Sound.SONAR_PING;
    }
}
