package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.dto.artwork.request.ArtworkCreateDTO;
import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.service.concrete.ArtworkServiceImpl;
import com.bkizilkaya.culturelbackend.utils.GenericUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@RequestMapping("/artworks")
public class ArtworkController {
    private final ArtworkServiceImpl artworkService;
    public ArtworkController(ArtworkServiceImpl artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    public ResponseEntity<List<ArtworkResponseDTO>> getAllArtworks() {
        return new ResponseEntity<>(artworkService.getAllArtworks(), OK);
    }

    @GetMapping("/{artworkId}/images")
    public ResponseEntity<List<FileData>> getArtworkImage(@PathVariable Long artworkId) {
        List<FileData> fileDataList = artworkService.getArtworkGivenId(artworkId).getFileDataList();
        return new ResponseEntity<>(fileDataList, OK);
    }

    @PostMapping("/{artworkId}/images")
    public ResponseEntity<String> addImageToArtwork(@PathVariable Long artworkId, @RequestParam("image") MultipartFile file) {
        Long artworkImagesArtworkId = artworkService.addImageToArtwork(artworkId, file);
        String returnMessage = artworkImagesArtworkId + " idli artworke resim eklendi";
        return new ResponseEntity<>(returnMessage, OK);
    }

    @PostMapping
    public ResponseEntity<ArtworkResponseDTO> addArtwork(@RequestBody ArtworkCreateDTO newArtwork) {
        ArtworkCreateDTO artworkCreateDTOResponseEntity = artworkService.addArtwork(newArtwork);
        ArtworkResponseDTO artworkResponseDTO = GenericUtil.artworkMapperForResponse(artworkCreateDTOResponseEntity);
        return new ResponseEntity<>(artworkResponseDTO, CREATED);
    }

    @GetMapping("/{artwork_id}")
    public ResponseEntity<ArtworkResponseDTO> getArtworkForGivenId(@PathVariable Long artwork_id) {
        ArtworkResponseDTO artworkFromDb = artworkService.getArtworkGivenId(artwork_id);
        return new ResponseEntity<>(artworkFromDb, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtworkResponseDTO> updateArtwork(@PathVariable Long id, @RequestBody ArtworkCreateDTO artworkCreateDTO) {
        ArtworkResponseDTO artworkResponseDTO = artworkService.updateArtwork(id, artworkCreateDTO);
        return new ResponseEntity<>(artworkResponseDTO, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Long id) {
        artworkService.deleteArtwork(id);
        return new ResponseEntity<>(OK);
    }

}
