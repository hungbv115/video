package com.example.video.service;

import com.example.video.io.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final FileStorage fileStorage;


    public Mono<byte[]> getImage(String name) {
        byte[] data = new byte[0];
        data = fileStorage.getFile(name);
        return Mono.just(data);
    }

    public Mono<Resource> getVideo(String title){
        byte[] data = new byte[0];
        data = fileStorage.getFile(title);
        ByteArrayResource resource = new ByteArrayResource(data);
        return Mono.fromSupplier(()->resource);
    }
}
