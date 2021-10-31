package com.cloudpi.cloudpi_backend.files.disk.services;

import com.google.common.primitives.Bytes;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

//TODO write serwice that is not a mock
@Service
public class MockDriveService implements DrivesService{

    private final Path root = Paths.get("/home/kamil/spring-test/");
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();

    @Override
    public Path getPathToSaveFile(Long fileSize, Long fileId) {
        return root.resolve(fileId.toString());
    }

    @Override
    public Path fileIdToPath(Long fileId) {
        return root.resolve(fileId.toString());
    }

}