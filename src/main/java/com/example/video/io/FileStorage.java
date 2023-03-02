package com.example.video.io;

import com.amazonaws.util.IOUtils;
import com.example.video.domain.dto.FileDTO;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

import static com.example.video.common.until.MessageUtils.getMessage;


public interface FileStorage {

    byte[] getFile(String fileName);

    FileDTO storeFile(String filename, String contentType, byte[] contents);

    void renameFile(String sourceFile, String desFile);

    void deleteFile(String file);

    default byte[] downloadTemplate(String fileName){
        var resource = new ClassPathResource("template/" + fileName);
        if (!resource.exists()){
            throw new FileNotFoundException(getMessage("file.download.notFound"));
        }
        try (InputStream defaultSign = resource.getInputStream()) {
            return IOUtils.toByteArray(defaultSign);
        } catch (IOException e) {
            throw new FileStorageException("error.system", e);
        }
    }
}
