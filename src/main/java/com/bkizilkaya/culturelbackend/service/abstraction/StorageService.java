package com.bkizilkaya.culturelbackend.service.abstraction;

import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    public List<FileDataResponseDTO> getAll();

    public Long saveFile(MultipartFile multiPartFile) throws IOException;

    public FileData findByName(String fileName);

    public byte[] downloadFileByteCode(String fileName) throws IOException;

    public void deleteFile(Long fileId);
}
