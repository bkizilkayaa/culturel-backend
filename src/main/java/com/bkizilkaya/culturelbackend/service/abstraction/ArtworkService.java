package com.bkizilkaya.culturelbackend.service.abstraction;

import com.bkizilkaya.culturelbackend.dto.artwork.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;

import java.util.List;

public interface ArtworkService {
    public ArtworkCreateDTO addArtwork(ArtworkCreateDTO artworkCreateDTO);

    public List<ArtworkResponseDTO> getAllArtworks();

    public ArtworkResponseDTO getArtworkGivenId(Long artworkId);

    public ArtworkResponseDTO updateArtwork(Long id, ArtworkCreateDTO artworkCreateDTO);

    public void deleteArtwork(Long id);

    public void removeArtworkImageFromArtwork(Long artworkId, Long imageId);

}
