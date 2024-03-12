package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.dto.artwork.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.exception.ArtworkNotFoundException;
import com.bkizilkaya.culturelbackend.model.Artwork;
import com.bkizilkaya.culturelbackend.repo.ArtworkRepository;
import com.bkizilkaya.culturelbackend.service.abstraction.ArtworkService;
import com.bkizilkaya.culturelbackend.utils.GenericUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArtworkServiceImpl implements ArtworkService {
    private final ArtworkRepository artworkRepository;


    public ArtworkServiceImpl(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    public ArtworkCreateDTO addArtwork(ArtworkCreateDTO artworkCreateDTO) {

        if (artworkCreateDTO.getParentId() != null) {
            getArtworkById(artworkCreateDTO.getParentId());
        }

        Artwork artwork = GenericUtil.artworkMapper(artworkCreateDTO);
        artworkRepository.save(artwork);
        return GenericUtil.artworkMapperForCreate(artwork);
    }

    public List<ArtworkResponseDTO> getAllArtworks() {
        List<ArtworkResponseDTO> artworkResponses = new ArrayList<>();
        List<Artwork> allArtworks = artworkRepository.findAll();
        for (Artwork artwork : allArtworks) {
            artworkResponses.add(GenericUtil.artworkMapperForResponse(artwork));
        }
        return artworkResponses;
    }

    public ArtworkResponseDTO getArtworkGivenId(Long artworkId) {
        Artwork artworkById = getArtworkById(artworkId);
        return GenericUtil.artworkMapperForResponse(artworkById);
    }

    public ArtworkResponseDTO updateArtwork(Long id, ArtworkCreateDTO artworkCreateDTO) {
        Artwork artworkById = getArtworkById(id);
        artworkById.setTitle(artworkCreateDTO.getTitle());
        artworkById.setContent(artworkCreateDTO.getContent());
        artworkById.setImages(artworkCreateDTO.getImages());
        return GenericUtil.artworkMapperForResponse(artworkById);
    }

    protected Artwork getArtworkById(Long artworkId) {
        return artworkRepository.findById(artworkId)
                .orElseThrow(() -> new ArtworkNotFoundException("artwork not found by id"));
    }

    public void deleteArtwork(Long id) {
        artworkRepository.deleteById(id);
    }
}

