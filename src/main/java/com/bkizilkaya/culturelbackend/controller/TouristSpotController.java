package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.dto.spot.response.TouristSpotResponseDTO;
import com.bkizilkaya.culturelbackend.service.concrete.TouristSpotServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/touristic-spots")
public class TouristSpotController {
    private final TouristSpotServiceImpl touristSpotService;

    public TouristSpotController(TouristSpotServiceImpl touristSpotService) {
        this.touristSpotService = touristSpotService;
    }

    @GetMapping()
    public ResponseEntity<List<TouristSpotResponseDTO>> getAllSpots() {
        return ResponseEntity.status(HttpStatus.OK).body(touristSpotService.getAllTouristicSpot());
    }
}
