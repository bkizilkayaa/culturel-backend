package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.exception.ValidationException;
import com.bkizilkaya.culturelbackend.service.abstraction.PathService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class PathServiceImpl implements PathService {
    private static final String DOT = ".";

    @Override
    public String generateFileName(MultipartFile file) {
        if (file.getOriginalFilename() != null) {
            return UUID.randomUUID() + DOT + getFileExtension(file.getOriginalFilename());
        } else {
            throw new ValidationException("file has no name");
        }
    }

    @Override
    public String getFileExtension(String originalFileName) {
        if (originalFileName != null && !originalFileName.isEmpty()) {
            return FilenameUtils.getExtension(originalFileName);
        }
        else{
            throw new ValidationException("file has no name");
        }
    }
}
