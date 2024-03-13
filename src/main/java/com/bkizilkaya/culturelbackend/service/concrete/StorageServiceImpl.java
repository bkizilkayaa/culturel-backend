package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.model.Image;
import com.bkizilkaya.culturelbackend.repo.FileDataRepository;
import com.bkizilkaya.culturelbackend.repo.StorageRepository;
import com.bkizilkaya.culturelbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl {

    private final StorageRepository storageRepository;

    private final FileDataRepository fileDataRepository;

    @Value("${local.file.path}")
    private String FOLDER_PATH;

    public StorageServiceImpl(StorageRepository storageRepository, FileDataRepository fileDataRepository) {
        this.storageRepository = storageRepository;
        this.fileDataRepository = fileDataRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = storageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .date(LocalDateTime.now())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }


    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = storageRepository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType()).build());

        file.transferTo(new File(filePath));

        return "file uploaded successfully : " + file.getName();
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = FOLDER_PATH + fileData.get().getName();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    public List<FileData> getAllFileData() {
        return fileDataRepository.findAll();
    }
}
