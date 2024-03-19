package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.dto.spot.request.TouristSpotCreateDTO;
import com.bkizilkaya.culturelbackend.dto.spot.response.TouristSpotResponseDTO;
import com.bkizilkaya.culturelbackend.mapper.TouristSpotMapper;
import com.bkizilkaya.culturelbackend.model.TouristSpot;
import com.bkizilkaya.culturelbackend.repo.TouristSpotRepository;
import com.bkizilkaya.culturelbackend.service.abstraction.TouristSpotService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TouristSpotServiceImpl implements TouristSpotService {
    private final TouristSpotRepository touristSpotRepository;
    private final FileDataServiceImpl fileDataService;


    public TouristSpotServiceImpl(TouristSpotRepository touristSpotRepository, FileDataServiceImpl fileDataService) {
        this.touristSpotRepository = touristSpotRepository;
        this.fileDataService = fileDataService;
    }


    @Override
    public TouristSpotResponseDTO addSpot(TouristSpotCreateDTO touristSpotCreateDto) {
        return null;
    }

    @Override
    public List<TouristSpotResponseDTO> getAllTouristicSpot() {
        List<TouristSpot> allSpots = touristSpotRepository.findAll();
        return allSpots.stream().map(TouristSpotMapper.INSTANCE::entityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public TouristSpotResponseDTO getSpotById(Long spotId) {
        return null;
    }

    @Override
    public TouristSpotResponseDTO updateSpot(Long spotId, TouristSpotCreateDTO touristSpotCreateDto) {
        return null;
    }

    @Override
    public void deleteSpot(Long spotId) {

    }

    @Override
    public void removeSpotImageFromSpot(Long spotId, Long imageId) {

    }
}