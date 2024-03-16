package com.bkizilkaya.culturelbackend.mapper;

import com.bkizilkaya.culturelbackend.dto.artwork.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.model.Artwork;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ArtworkMapper {
    public static Artwork artworkMapper(ArtworkCreateDTO artworkCreateDTO) {
        Artwork artwork = new Artwork();
        artwork.setContent(artworkCreateDTO.getContent());
        artwork.setID(artworkCreateDTO.getId());
        artwork.setTitle(artworkCreateDTO.getTitle());
        artwork.setCreateDate(LocalDateTime.now());
        artwork.setParentId(artworkCreateDTO.getParentId());
        artwork.setAuthorId(artworkCreateDTO.getAuthorId());
        artwork.setFileData(artworkCreateDTO.getFileDataList());

        return artwork;
    }

    public static ArtworkCreateDTO artworkMapperForCreate(Artwork artwork) {
        return ArtworkCreateDTO.builder()
                .Id(artwork.getID())
                .createDate(LocalDateTime.now())
                .authorId(artwork.getAuthorId())
                .content(artwork.getContent())

                .title(artwork.getTitle())
                .fileDataList(artwork.getFiles())
                .build();
    }

    public static ArtworkResponseDTO artworkMapperForResponse(Artwork artwork) {
        return ArtworkResponseDTO.builder()
                .Id(artwork.getID())
                .authorId(artwork.getAuthorId())
                .fileDataList(artwork.getFiles().stream().map(FileDataMapper::fileDataMapperForResponseDto).collect(Collectors.toList()))
                .content(artwork.getContent())
                .createDate(LocalDateTime.now())
                .title(artwork.getTitle())
                .build();
    }

    public static ArtworkResponseDTO artworkMapperForResponse(ArtworkCreateDTO artworkCreateDTO) {
        return ArtworkResponseDTO.builder()
                .Id(artworkCreateDTO.getId())
                .createDate(artworkCreateDTO.getCreateDate())
                .authorId(artworkCreateDTO.getAuthorId())
                .content(artworkCreateDTO.getContent())
                .fileDataList(artworkCreateDTO.getFileDataList().stream().map(FileDataMapper::fileDataMapperForResponseDto).collect(Collectors.toList()))
                .title(artworkCreateDTO.getTitle())
                .build();
    }
}
