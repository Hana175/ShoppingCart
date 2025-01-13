package com.example.shoppingcart.controller;


import com.example.shoppingcart.dto.ImageDto;
import com.example.shoppingcart.response.ApiResponse;
import com.example.shoppingcart.service.image.iImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequiredArgsConstructor //to inject constructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final iImageService imageService;


    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) //used to return data for frontend
    {
        try {
            List<ImageDto> imageDtos = imageService.saveImages(files, productId);
            return ResponseEntity.ok(new ApiResponse("Upload successful", imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!", e.getMessage()));
        }
    }
}
