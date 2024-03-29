package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.dto.artwork.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.exception.ArtworkNotFoundException;
import com.bkizilkaya.culturelbackend.exception.SpecifiedFileNotFoundException;
import com.bkizilkaya.culturelbackend.mapper.ArtworkMapper;
import com.bkizilkaya.culturelbackend.model.Artwork;
import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.repo.ArtworkRepository;
import com.bkizilkaya.culturelbackend.service.abstraction.ArtworkService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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
    public List<ArtworkResponseDTO> getAllArtworks() {
        List<Artwork> allArtworks = artworkRepository.findAll();
        return allArtworks.stream().map(ArtworkMapper.INSTANCE::artworkToResponseDto).collect(Collectors.toList());
    }


    @Override
    public ArtworkResponseDTO addArtwork(ArtworkCreateDTO artworkCreateDTO) {
        if (artworkCreateDTO.getParentId() != null) {
            getArtworkById(artworkCreateDTO.getParentId());
        }

        Artwork artwork = ArtworkMapper.INSTANCE.dtoToEntity(artworkCreateDTO);
        artworkRepository.save(artwork);
        return ArtworkMapper.INSTANCE.artworkToResponseDto(artwork);
    }

    @Override
    public ArtworkResponseDTO getArtworkGivenId(Long artworkId) {
        Artwork artworkById = getArtworkById(artworkId);
        return ArtworkMapper.INSTANCE.artworkToResponseDto(artworkById);
    }

    @Override
    public ArtworkResponseDTO updateArtwork(Long id, ArtworkCreateDTO artworkCreateDTO) {
        Artwork artworkFromDb = getArtworkById(id);

        artworkFromDb.setTitle(artworkCreateDTO.getTitle());
        artworkFromDb.setDescription(artworkCreateDTO.getDescription());
        artworkFromDb.setContent(artworkCreateDTO.getContent());
        artworkFromDb.setAuthorId(artworkCreateDTO.getAuthorId());
        artworkFromDb.setParentId(artworkCreateDTO.getParentId());
        artworkFromDb.setModifiedDate(LocalDateTime.now());

        artworkRepository.save(artworkFromDb);
        return ArtworkMapper.INSTANCE.artworkToResponseDto(artworkFromDb);
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
            artwork.getFileData().add(fileData);
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
        if (artwork.getFileData().stream().noneMatch(fileData -> fileData.getId().equals(imageId))) {
            throw new SpecifiedFileNotFoundException("FileData not found in Object.");
        } else {
            artwork.setFileData(artwork.getFileData()
                    .stream()
                    .filter(fileData -> !fileData.getId().equals(imageId))
                    .collect(Collectors.toList()));

            fileDataFromDb.setArtworkImages(null);
        }
    }

    protected Artwork getArtworkById(Long artworkId) {
        return artworkRepository.findById(artworkId)
                .orElseThrow(() -> new ArtworkNotFoundException("artwork not found by id"));
    }
}