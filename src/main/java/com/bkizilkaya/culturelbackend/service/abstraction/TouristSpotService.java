package com.bkizilkaya.culturelbackend.service.abstraction;

import com.bkizilkaya.culturelbackend.dto.spot.request.TouristSpotCreateDTO;
import com.bkizilkaya.culturelbackend.dto.spot.response.TouristSpotResponseDTO;

import java.util.List;

public interface TouristSpotService {
    TouristSpotResponseDTO addSpot(TouristSpotCreateDTO touristSpotCreateDto);

    List<TouristSpotResponseDTO> getAllTouristicSpot();

    TouristSpotResponseDTO getSpotById(Long spotId);

    TouristSpotResponseDTO updateSpot(Long spotId, TouristSpotCreateDTO touristSpotCreateDto);

    void deleteSpot(Long spotId);

    void removeSpotImageFromSpot(Long spotId, Long imageId);

}
