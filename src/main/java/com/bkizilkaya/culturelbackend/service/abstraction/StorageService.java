package com.bkizilkaya.culturelbackend.service.abstraction;

import com.bkizilkaya.culturelbackend.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    public List<FileData> getAll();
    public String saveFile(MultipartFile multiPartFile) throws IOException;
    public FileData findByName(String fileName);

    public byte[] downloadFileByteCode(String fileName) throws IOException;
}
