package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.model.Image;
import com.bkizilkaya.culturelbackend.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // display image
    @GetMapping("/display/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable("id") Long id) throws IOException, SQLException {
        Image image = getImageById(id);
        byte[] imageBytes = getImageBytes(image);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    private Image getImageById(Long id) {
        return imageService.viewImageById(id);
    }

    private static byte[] getImageBytes(Image image) throws SQLException {
        return image.getImage().getBytes(1, (int) image.getImage().length());
    }

    @GetMapping("/{artworkId}")
    public ResponseEntity<byte[]> getImagesByArtworkId(@PathVariable("artworkId") Long artworkId) throws IOException, SQLException {
        List<Image> imageList = imageService.getImageListByArtworkId(artworkId);

        if (imageList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Birleştirilmiş byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < imageList.size(); i++) {
            byte[] imageBytes = getImageBytes(imageList.get(i));
            String imageHeader = "Image-" + i + "\n";
            outputStream.write(imageHeader.getBytes());
            outputStream.write(imageBytes);
        }
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(outputStream.toByteArray());
    }
    // add image - post
    @PostMapping("/add/{artworkId}")
    public Image createImage(@PathVariable Long artworkId, @RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Image image = new Image();
        image.setImage(blob);
        return imageService.createImage(image, artworkId);
    }

}
