package com.bkizilkaya.culturelbackend.service.abstraction;

import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    List<FileDataResponseDTO> getAll();

    Long saveFile(MultipartFile multiPartFile) throws IOException;

    FileData findByName(String fileName);

    String getFilePathFromStorage(String fileName);

    void deleteFile(Long fileId);

    List<Long> findUnusedFilesId();

    List<String> findUnusedFilesName();
}
