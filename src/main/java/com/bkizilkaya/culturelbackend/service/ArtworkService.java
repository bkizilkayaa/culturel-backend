package com.bkizilkaya.culturelbackend.service;

import com.bkizilkaya.culturelbackend.dto.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.model.Artwork;
import com.bkizilkaya.culturelbackend.repo.ArtworkRepository;
import com.bkizilkaya.culturelbackend.utils.GenericUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private GenericUtil genericUtil = new GenericUtil();

    public ArtworkService(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }
    public ArtworkCreateDTO addArtwork(ArtworkCreateDTO artworkCreateDTO){
        Artwork artwork = genericUtil.artworkMapper(artworkCreateDTO);
        artworkRepository.save(artwork);
        return genericUtil.artworkMapperForCreate(artwork);
    }
    public List<ArtworkResponseDTO> getAllArtworks(){
        List<ArtworkResponseDTO> artworkResponses = new ArrayList<>();
        List<Artwork> allArtworks = artworkRepository.findAll();
        for (Artwork artwork : allArtworks){
            artworkResponses.add(genericUtil.artworkMapperForResponse(artwork));
        }
       return artworkResponses;
    }
}

