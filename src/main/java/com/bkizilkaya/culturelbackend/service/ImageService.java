package com.bkizilkaya.culturelbackend.service;

import com.bkizilkaya.culturelbackend.exception.ImageNotFoundException;
import com.bkizilkaya.culturelbackend.model.Image;
import com.bkizilkaya.culturelbackend.repo.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image createImage(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> viewAllImages() {
        return (List<Image>) imageRepository.findAll();
    }

    public Image viewImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("image not found by id"));
    }
}
