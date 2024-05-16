package com.skillstorm.taxservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {
        // Bucket names are globally unique. The bucket you try to access must be within
        // the region specified here:
        return S3Client.builder().region(Region.US_EAST_1).build();
    }
}