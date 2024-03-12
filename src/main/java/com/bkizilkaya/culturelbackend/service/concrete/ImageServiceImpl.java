package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.exception.ImageNotFoundException;
import com.bkizilkaya.culturelbackend.model.Artwork;
import com.bkizilkaya.culturelbackend.model.Image;
import com.bkizilkaya.culturelbackend.repo.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ImageServiceImpl {
    private final ImageRepository imageRepository;
    private final ArtworkServiceImpl artworkService;

    public ImageServiceImpl(ImageRepository imageRepository, ArtworkServiceImpl artworkService) {
        this.imageRepository = imageRepository;
        this.artworkService = artworkService;
    }

    public Image createImage(Image image, Long artworkId) {
        Artwork artwork = artworkService.getArtworkById(artworkId);
        artwork.getImages().add(image);
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

    public List<Image> getImageListByArtworkId(Long artworkId) {
        Artwork artwork = artworkService.getArtworkById(artworkId);
        return artwork.getImages();
    }
}
