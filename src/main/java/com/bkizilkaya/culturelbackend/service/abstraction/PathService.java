package com.bkizilkaya.culturelbackend.service.abstraction;

import org.springframework.web.multipart.MultipartFile;

public interface PathService {

    String generateFileName(MultipartFile file);

    String getFileExtension(String originalFileName);

}
