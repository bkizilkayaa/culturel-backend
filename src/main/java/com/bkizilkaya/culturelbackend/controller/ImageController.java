package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.model.Image;
import com.bkizilkaya.culturelbackend.service.concrete.ImageServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

@RestController
@CrossOrigin
@RequestMapping("/depreceated")
public class ImageController {
    private final ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    // display image
    @GetMapping("/display/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable("id") Long id) throws IOException, SQLException {
        Image image = getImageById(id);
        //byte[] imageBytes = getImageBytes(image);
        //return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        return null;
    }

    private Image getImageById(Long id) {
        return imageService.viewImageById(id);
    }

    private static byte[] getImageBytes(Image image) throws SQLException {
        //return image.getImage().getBytes(1, (int) image.getImage().length());
        return new byte[0];
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
            //byte[] imageBytes = getImageBytes(imageList.get(i));
            String imageHeader = "Image-" + i + "\n";
            outputStream.write(imageHeader.getBytes());
            //outputStream.write(imageBytes);
        }
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(outputStream.toByteArray());
    }

    // add image - post
    @PostMapping("/add/{artworkId}")
    public Image createImage(@PathVariable Long artworkId, @RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException {
        byte[] bytes = file.getBytes();

        // Sıkıştırma işlemi ama bug var suan
        //byte[] compressedBytes = compress(bytes);

        Blob blob = new SerialBlob(bytes);
        Image image = new Image();
        //image.setImage(blob);
        return imageService.createImage(image, artworkId);
    }

    private byte[] compress(byte[] input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DeflaterOutputStream dos = new DeflaterOutputStream(baos)) {
            dos.write(input);
        }
        return baos.toByteArray();
    }

}
