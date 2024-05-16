package com.skillstorm.taxservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Service
public class S3Service {

    private final S3Client s3;
    private final String imageBucket = "";

    @Autowired
    public S3Service(S3Client s3 /*,@Value("${IMAGE_BUCKET}") String imageBucket*/) {
        this.s3 = s3;
        //this.imageBucket = imageBucket;
    }

    // Upload file to S3 bucket:
    public void uploadFile(String key, byte[] file) {
        s3.putObject(
                PutObjectRequest.builder()
                        .bucket(imageBucket)
                        .key(key)
                        .build(),
                RequestBody.fromBytes(file)
        );
    }

    // Download file from S3 bucket:
    public InputStream getObject(String key) {
        return s3.getObject(
                GetObjectRequest.builder()
                        .bucket(imageBucket)
                        .key(key)
                        .build()
        );
    }
}
