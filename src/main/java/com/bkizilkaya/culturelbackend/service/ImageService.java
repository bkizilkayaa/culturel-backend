package com.bkizilkaya.culturelbackend.service;

import com.bkizilkaya.culturelbackend.dto.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.exception.ImageNotFoundException;
import com.bkizilkaya.culturelbackend.model.Artwork;
import com.bkizilkaya.culturelbackend.model.Image;
import com.bkizilkaya.culturelbackend.repo.ArtworkRepository;
import com.bkizilkaya.culturelbackend.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final  ArtworkService artworkService;

    public ImageService(ImageRepository imageRepository, ArtworkService artworkService){
        this.imageRepository = imageRepository;
        this.artworkService = artworkService;
    }

    public Image createImage(Image image,Long artworkId) {
        Artwork artwork = artworkService.getArtworkById(artworkId);
        artwork.getImages().add(image);
        artwork.setImages(artwork.getImages());
        image.setArtwork(artwork);

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
