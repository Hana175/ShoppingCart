package com.example.shoppingcart.service.image;


import com.example.shoppingcart.dto.ImageDto;
import com.example.shoppingcart.exceptions.ResourceNotFoundException;
import com.example.shoppingcart.model.Image;
import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ImageRepository;
import com.example.shoppingcart.service.product.iProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageService implements iImageService{
    private final ImageRepository imageRepository;
    private iProductService productService;

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No image found with the id: "+ id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository:: delete, ()->
        {throw new ResourceNotFoundException("No image found with this id: "+ id);});
    }

    @Override
    public Image saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> imageDtos = new ArrayList<>();
        for(MultipartFile file: files){
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String downloadUrl = "/api/v1/image/download/" +image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl("/api/v1/image/download/" + savedImage.getId());

            }
            catch(){

            }
        }
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImage(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
