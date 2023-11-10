package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.dto.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.service.ArtworkService;
import com.bkizilkaya.culturelbackend.utils.GenericUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@RequestMapping("/artworks")
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
        ArtworkCreateDTO artworkCreateDTOResponseEntity = artworkService.addArtwork(newArtwork);
        ArtworkResponseDTO artworkResponseDTO = GenericUtil.artworkMapperForResponse(artworkCreateDTOResponseEntity);
        return new ResponseEntity<>(artworkResponseDTO, CREATED);
    }
    @GetMapping("/{artwork_id}")
    public ResponseEntity<ArtworkResponseDTO> getArtworkForGivenId(@PathVariable Long artwork_id){
        ArtworkResponseDTO artworkFromDb = artworkService.getArtworkGivenId(artwork_id);
        return new ResponseEntity<>(artworkFromDb, OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ArtworkResponseDTO> updateLecturer(@PathVariable Long id, @RequestBody ArtworkCreateDTO artworkCreateDTO){
        ArtworkResponseDTO artworkResponseDTO = artworkService.updateArtwork(id, artworkCreateDTO);
        return new ResponseEntity<>(artworkResponseDTO, OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateLecturer(@PathVariable Long id){
        artworkService.deleteArtwork(id);
        return new ResponseEntity<>(OK);
    }

}
