package com.bkizilkaya.culturelbackend.service.abstraction;

import org.springframework.web.multipart.MultipartFile;

public interface PathService {

    public String generateFileName(MultipartFile file);

    public String getFileExtension(String originalFilename);

}
