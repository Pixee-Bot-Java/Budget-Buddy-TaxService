package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.exceptions.UnableToReadStreamException;
import com.skillstorm.taxservice.models.W2;
import com.skillstorm.taxservice.repositories.W2Repository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static software.amazon.awssdk.utils.IoUtils.toByteArray;

@Service
public class W2Service {

    private final W2Repository w2Repository;
    private final S3Service s3Service;

    @Autowired
    public W2Service(W2Repository w2Repository, S3Service s3Service) {
        this.w2Repository = w2Repository;
        this.s3Service = s3Service;
    }

    // Add new W2 by UserId and Year:
    public W2 addW2(W2 newW2) {
        return w2Repository.saveAndFlush(newW2);
    }

    // Find W2 by ID:
    public W2 findById(int id) {
        return w2Repository.findById(id)
                .orElseThrow(() -> new NotFoundException("w2.not.found"));
    }

    // Find all W2s by UserId:
    public List<W2> findAllByUserId(int userId) {
        return w2Repository.findAllByUserId(userId);
    }

    // Find all W2s by UserId and Year:
    public List<W2> findAllByUserIdAndYear(int userId, int year) {
        return w2Repository.findAllByUserIdAndYear(userId, year);
    }

    // Update W2 by ID:
    public W2 updateById(int id, W2 updatedW2) {
        // Verify W2 exists:
        findById(id);
        updatedW2.setId(id);
        return w2Repository.saveAndFlush(updatedW2);
    }

    // Delete W2 by Id:
    public void deleteById(int id) {
        // Verify W2 exists:
        findById(id);
        w2Repository.deleteById(id);
    }

    // Upload image to S3:
    public String uploadImage(int id, byte[] image, String contentType) {
        W2 w2 =findById(id);
        String imageKey = defineKey(w2, contentType);
        s3Service.uploadFile(imageKey, image);
        w2.setImageKey(imageKey);
        w2Repository.saveAndFlush(w2);
        return imageKey;
    }

    // Utility method to build an image key based on the W2:
    private String defineKey(W2 w2, String contentType) {
        StringBuilder sb = new StringBuilder();
        sb.append("w2s/");
        sb.append(w2.getUserId());
        sb.append("/");
        sb.append(w2.getId());
        sb.append(".");
        sb.append(contentType.split("/")[1]);

        return sb.toString();
    }

    // Download image from S3:
    @SneakyThrows
    public Resource downloadImage(int id) {
        W2 w2 = findById(id);
        InputStream inputStream = s3Service.getObject(w2.getImageKey());
        byte[] byteArray;
        try {
            byteArray = toByteArray(inputStream);
        } catch (IOException e) {
            throw new UnableToReadStreamException("stream.read.unable");
        }

        return new ByteArrayResource(byteArray);
    }
}
