package com.bkizilkaya.culturelbackend.utils;

import com.bkizilkaya.culturelbackend.dto.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.model.Artwork;

public class GenericUtil {
    //builder pattern kullanilabilirdi, az field var diye geçtim.
    public static Artwork artworkMapper(ArtworkCreateDTO artworkCreateDTO){
        Artwork artwork = new Artwork();
        artwork.setContent(artworkCreateDTO.getContent());
        artwork.setTitle(artworkCreateDTO.getTitle());
        artwork.setCreateDate(artworkCreateDTO.getCreateDate());
        artwork.setAuthorId(artworkCreateDTO.getAuthorId());
        artwork.setCoordinateX(artworkCreateDTO.getCoordinateX());
        artwork.setCoordinateY(artworkCreateDTO.getCoordinateY());
        return artwork;
    }
    public static ArtworkCreateDTO artworkMapperForCreate(Artwork artwork){
        ArtworkCreateDTO artworkCreateDTO = new ArtworkCreateDTO();
        artworkCreateDTO.setCreateDate(artwork.getCreateDate());
        artworkCreateDTO.setTitle(artwork.getTitle());
        artworkCreateDTO.setAuthorId(artwork.getAuthorId());
        artworkCreateDTO.setContent(artwork.getContent());
        artworkCreateDTO.setCoordinateY(artwork.getCoordinateY());
        artworkCreateDTO.setCoordinateX(artwork.getCoordinateX());
        return artworkCreateDTO;
    }
    public static ArtworkResponseDTO artworkMapperForResponse(Artwork artwork){
        ArtworkResponseDTO artworkResponseDTO = new ArtworkResponseDTO();
        artworkResponseDTO.setCreateDate(artwork.getCreateDate());
        artworkResponseDTO.setTitle(artwork.getTitle());
        artworkResponseDTO.setAuthorId(artwork.getAuthorId());
        artworkResponseDTO.setContent(artwork.getContent());
        artworkResponseDTO.setCoordinateY(artwork.getCoordinateY());
        artworkResponseDTO.setCoordinateX(artwork.getCoordinateX());
        return artworkResponseDTO;
    }
}
