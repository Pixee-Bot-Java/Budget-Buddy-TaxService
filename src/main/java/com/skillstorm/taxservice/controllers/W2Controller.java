package com.skillstorm.taxservice.controllers;

import com.skillstorm.taxservice.dtos.W2Dto;
import com.skillstorm.taxservice.exceptions.UndeterminedContentException;
import com.skillstorm.taxservice.services.W2Service;
import lombok.SneakyThrows;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/w2s")
public class W2Controller {

    private final W2Service w2Service;

    @Autowired
    public W2Controller(W2Service w2Service) {
        this.w2Service = w2Service;
    }

    // Add new W2s or update existing W2s. Can also be used to delete W2s by sending an empty list:
    @PostMapping
    public ResponseEntity<List<W2Dto>> addW2sByTaxReturnId(@RequestParam("taxReturnId") int taxReturnId, @RequestBody List<W2Dto> updatedW2s) {
        return ResponseEntity.ok(w2Service.updateAllByTaxReturnId(taxReturnId, updatedW2s));
    }

    // Find W2 by ID:
    @GetMapping("/{id}")
    public ResponseEntity<W2Dto> findW2ById(@PathVariable("id") int id) {
        return ResponseEntity.ok(w2Service.findById(id));
    }

    // Find all W2s by UserId:
    @GetMapping("/w2/{userId}")
    public ResponseEntity<List<W2Dto>> findAllW2sByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(w2Service.findAllByUserId(userId));
    }

    // Find all W2s by UserId and Year:
    @GetMapping("w2/{userId}/{year}")
    public ResponseEntity<List<W2Dto>> findAllW2sByUserId(@PathVariable("userId") int userId, @PathVariable("year") int year) {
        return ResponseEntity.ok(w2Service.findAllByUserIdAndYear(userId, year));
    }

    // Find all W2s by Tax Return ID:
    @GetMapping()
    public ResponseEntity<List<W2Dto>> findAllW2sByTaxReturnId(@RequestParam("taxReturnId") int taxReturnId) {
        return ResponseEntity.ok(w2Service.findAllByTaxReturnId(taxReturnId));
    }

    // Delete W2 by ID:
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        w2Service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Upload image to S3:
    @PostMapping("/{id}/image")
    public ResponseEntity<Void> uploadImageToS3(@PathVariable("id") int id, @RequestBody byte[] image,
                                              @RequestHeader("Content-Type") String contentType) {
        String imageKey = w2Service.uploadImage(id, image, contentType);
        return ResponseEntity.created(URI.create("/" + imageKey)).build();
    }

    // Download image from S3:
    @SneakyThrows
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable("id") int id) {
        Resource imageResource = w2Service.downloadImage(id);
        String contentType = determineContentType(imageResource);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(imageResource);
    }

    // Utility method to determine content type of returned image (image/png, image/jpg, application/pdf, etc):
    @SneakyThrows
    private String determineContentType(Resource imageResource) {
        Tika tika = new Tika();
        try {
            return tika.detect(imageResource.getInputStream());
        } catch (IOException e) {
            throw new UndeterminedContentException("unknown.content");
        }
    }
}
