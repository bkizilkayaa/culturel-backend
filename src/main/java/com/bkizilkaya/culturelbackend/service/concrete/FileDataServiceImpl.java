package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.exception.SpecifiedFileNotFoundException;
import com.bkizilkaya.culturelbackend.exception.ValidationException;
import com.bkizilkaya.culturelbackend.mapper.FileDataMapper;
import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.repo.FileDataRepository;
import com.bkizilkaya.culturelbackend.service.abstraction.StorageService;
import com.bkizilkaya.culturelbackend.utils.ImageValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileDataServiceImpl implements StorageService {
    @Value("${local.file.path}")
    private String FOLDER_PATH;
    private final FileDataRepository fileDataRepository;
    private final ImageValidator imageValidator;
    private final PathServiceImpl pathService;

    public FileDataServiceImpl(FileDataRepository fileDataRepository, ImageValidator imageValidator, PathServiceImpl pathService) {
        this.fileDataRepository = fileDataRepository;
        this.imageValidator = imageValidator;
        this.pathService = pathService;
    }

    @Override
    public List<FileDataResponseDTO> getAll() {
        List<FileData> fileDataListFromDb = fileDataRepository.findAll();
        return fileDataListFromDb.stream().map(FileDataMapper.INSTANCE::fileDataToResponseDto).collect(Collectors.toList());
    }

    @Override
    public Long saveFile(MultipartFile multiPartFile) throws IOException {
        if (!imageValidator.isImage(multiPartFile)) {
            throw new ValidationException("not a valid image");
        }
        if (!imageValidator.isFileSizeValid(multiPartFile)) {
            throw new ValidationException("Maximum file size reached : 10MB+");
        }
        String fileName = pathService.generateFileName(multiPartFile);
        String filePath = FOLDER_PATH + fileName;
        Long fileId = saveFileDataToDatabase(multiPartFile, fileName);
        multiPartFile.transferTo(new File(filePath));
        return fileId;
    }

    @Override
    public String getFilePathFromStorage(String fileName){
        FileData fileData = findByName(fileName);
        return FOLDER_PATH + fileData.getName();
    }

    private Long saveFileDataToDatabase(MultipartFile multiPartFile, String fileName) {
        pathService.getFileExtension(multiPartFile.getOriginalFilename());
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(fileName)
                .createDate(LocalDateTime.now())
                .type(multiPartFile.getContentType()).build());
        return fileData.getId();
    }

    @Override
    public FileData findByName(String fileName) {
        return fileDataRepository.findByName(fileName)
                .orElseThrow(() -> new SpecifiedFileNotFoundException("file not found with name : ", fileName));
    }

    protected FileData findById(Long fileId) {
        return fileDataRepository.findById(fileId)
                .orElseThrow(() -> new SpecifiedFileNotFoundException("file not found with id"));
    }

    @Override
    public void deleteFile(Long fileId) {
        FileData fileDataFromDb = findById(fileId);
        if (fileDataFromDb != null) {
            fileDataRepository.deleteById(fileId);
        } else {
            throw new SpecifiedFileNotFoundException("file not found with id");
        }
    }

    @Override
    public List<Long> findUnusedFilesId() {
        return fileDataRepository.findUnusedFilesId().orElseThrow(()-> new RuntimeException("an error occured when fetching data from db"));
    }

    @Override
    public List<String> findUnusedFilesName() {
        return fileDataRepository.findUnusedFilesName().orElseThrow(()-> new RuntimeException("an error occured when fetching data from db"));
    }

}
