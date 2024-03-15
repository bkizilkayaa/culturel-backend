package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.exception.SpecifiedFileNotFoundException;
import com.bkizilkaya.culturelbackend.exception.ValidationException;
import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.repo.FileDataRepository;
import com.bkizilkaya.culturelbackend.service.abstraction.StorageService;
import com.bkizilkaya.culturelbackend.utils.FileDataMapper;
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
        return fileDataListFromDb.stream().map(FileDataMapper::fileDataMapperForResponseDto).collect(Collectors.toList());
    }

    @Override
    public Long saveFile(MultipartFile multiPartFile) throws IOException {
        if (!imageValidator.isImage(multiPartFile) || !imageValidator.isFileSizeValid(multiPartFile)) {
            throw new ValidationException("not a valid image");
        }
        String fileName = pathService.generateFileName(multiPartFile);
        String filePath = FOLDER_PATH + fileName;
        Long fileId = saveFileDataToDatabase(multiPartFile, fileName);

        if (fileId == null) {
            throw new ValidationException("file is not valid");
        }
        multiPartFile.transferTo(new File(filePath));
        return fileId;
    }

    @Override
    public byte[] downloadFileByteCode(String fileName) throws IOException {
        FileData fileData = findByName(fileName);
        String filePath = FOLDER_PATH + fileData.getName();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    private Long saveFileDataToDatabase(MultipartFile multiPartFile, String fileName) {
        String fileExtension = pathService.getFileExtension(multiPartFile.getOriginalFilename());
        if (fileExtension != null) {
            FileData fileData = fileDataRepository.save(FileData.builder()
                    .name(fileName)
                    .createDate(LocalDateTime.now())
                    .type(multiPartFile.getContentType()).build());
            return fileData.getID();
        }
        return null;
    }

    @Override
    public FileData findByName(String fileName) {
        return fileDataRepository.findByName(fileName)
                .orElseThrow(() -> new SpecifiedFileNotFoundException("file not found with name : ", fileName));
    }

    protected FileData findById(Long fileId) {
        return fileDataRepository.findById(fileId)
                .orElseThrow(() -> new SpecifiedFileNotFoundException("file not found with id : "));
    }

}
