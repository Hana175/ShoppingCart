package com.example.shoppingcart.service.image;

import com.example.shoppingcart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface iImageService {
    Image getImage(Long imageId);
    void deleteImageById(Long id);
    Image saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long productId);
}
