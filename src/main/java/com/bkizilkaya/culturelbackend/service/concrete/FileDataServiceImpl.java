package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.exception.SpecifiedFileNotFoundException;
import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.repo.FileDataRepository;
import com.bkizilkaya.culturelbackend.service.abstraction.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class FileDataServiceImpl implements StorageService {
    private final FileDataRepository fileDataRepository;

    @Value("${local.file.path}")
    private String FOLDER_PATH;

    public FileDataServiceImpl(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    public List<FileData> getAll() {
        return fileDataRepository.findAll();
    }

    @Override
    public String saveFile(MultipartFile multiPartFile) throws IOException {
        String filePath = FOLDER_PATH + multiPartFile.getOriginalFilename();
        saveFileDataToDatabase(multiPartFile);
        multiPartFile.transferTo(new File(filePath));
        return "File uploaded successfully : " + multiPartFile.getName();
    }

    @Override
    public byte[] downloadFileByteCode(String fileName) throws IOException {
        FileData fileData = findByName(fileName);
        String filePath = FOLDER_PATH + fileData.getName();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    private void saveFileDataToDatabase(MultipartFile multiPartFile) {
        fileDataRepository.save(FileData.builder()
                .name(multiPartFile.getOriginalFilename())
                .type(multiPartFile.getContentType()).build());
    }

    @Override
    public FileData findByName(String fileName) {
        return fileDataRepository.findByName(fileName)
                .orElseThrow(() -> new SpecifiedFileNotFoundException("file not found with name : ", fileName));
    }

}
