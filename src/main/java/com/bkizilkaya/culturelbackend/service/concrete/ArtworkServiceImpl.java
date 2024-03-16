package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.dto.artwork.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.exception.ArtworkNotFoundException;
import com.bkizilkaya.culturelbackend.model.Artwork;
import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.repo.ArtworkRepository;
import com.bkizilkaya.culturelbackend.service.abstraction.ArtworkService;
import com.bkizilkaya.culturelbackend.utils.ArtworkMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArtworkServiceImpl implements ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final FileDataServiceImpl fileDataService;


    public ArtworkServiceImpl(ArtworkRepository artworkRepository, FileDataServiceImpl fileDataService) {
        this.artworkRepository = artworkRepository;
        this.fileDataService = fileDataService;
    }

    @Override
    public ArtworkResponseDTO addArtwork(ArtworkCreateDTO artworkCreateDTO) {
        if (artworkCreateDTO.getParentId() != null) {
            getArtworkById(artworkCreateDTO.getParentId());
        }

        Artwork artwork = ArtworkMapper.artworkMapper(artworkCreateDTO);
        artworkRepository.save(artwork);
        return ArtworkMapper.artworkMapperForResponse(artwork);
    }

    @Override
    public List<ArtworkResponseDTO> getAllArtworks() {
        List<Artwork> allArtworks = artworkRepository.findAll();
        return allArtworks.stream().map(ArtworkMapper::artworkMapperForResponse).collect(Collectors.toList());
    }

    @Override
    public ArtworkResponseDTO getArtworkGivenId(Long artworkId) {
        Artwork artworkById = getArtworkById(artworkId);
        return ArtworkMapper.artworkMapperForResponse(artworkById);
    }

    @Override
    public ArtworkResponseDTO updateArtwork(Long id, ArtworkCreateDTO artworkCreateDTO) {
        Artwork artworkFromDb = getArtworkById(id);
        artworkFromDb.setTitle(artworkCreateDTO.getTitle());
        artworkFromDb.setContent(artworkCreateDTO.getContent());
        artworkFromDb.setFileData(artworkCreateDTO.getFileDataList());
        return ArtworkMapper.artworkMapperForResponse(artworkFromDb);
    }

    @Override
    public void deleteArtwork(Long id) {
        Artwork artworkFromDb = getArtworkById(id);
        artworkRepository.deleteById(id);
    }

    @Transactional
    public Long addImageToArtwork(Long artworkId, MultipartFile multipartFile) {
        try {
            Long fileId = fileDataService.saveFile(multipartFile);
            FileData fileData = fileDataService.findById(fileId);
            Artwork artwork = getArtworkById(artworkId);


            fileData.setArtworkImages(artwork);
            artwork.getFiles().add(fileData);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return artworkId;
    }

    @Override
    @Transactional
    public void removeArtworkImageFromArtwork(Long artworkId, Long imageId) {
        Artwork artwork = getArtworkById(artworkId);
        FileData fileDataFromDb = fileDataService.findById(imageId);
        artwork.getFiles().remove(fileDataFromDb);
        fileDataFromDb.setArtworkImages(null);
    }

    protected Artwork getArtworkById(Long artworkId) {
        return artworkRepository.findById(artworkId)
                .orElseThrow(() -> new ArtworkNotFoundException("artwork not found by id"));
    }
}