package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.dto.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.service.ArtworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/products")
public class ArtworkController {
    private final ArtworkService artworkService;

    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    public ResponseEntity<List<ArtworkResponseDTO>> getAllArtworks(){
        return new ResponseEntity<>(artworkService.getAllArtworks(), OK);
    }
    @PostMapping
    public ResponseEntity<ArtworkResponseDTO> addProduct(@RequestBody ArtworkCreateDTO newArtwork){
        ResponseEntity<ArtworkCreateDTO> artworkCreateDTOResponseEntity = new ResponseEntity<>(artworkService.addArtwork(newArtwork), CREATED);
        return null;
    }
}
