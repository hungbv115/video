package com.example.video.controller;

import com.example.video.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StreamingService service;


    @GetMapping("/v")
    public ResponseEntity<Mono<byte[]>> getImage(@RequestParam String filename) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(service.getImage(filename));
    }

    @GetMapping(value = "video", produces = "video/mp4")
    public Mono<Resource> getVideos(@RequestParam String title, @RequestHeader("Range") String range) {
        System.out.println("range in bytes() : " + range);
        return service.getVideo(title);
    }

}
