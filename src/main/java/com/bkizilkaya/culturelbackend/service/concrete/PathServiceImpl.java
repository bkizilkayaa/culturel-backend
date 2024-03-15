package com.bkizilkaya.culturelbackend.service.concrete;

import com.bkizilkaya.culturelbackend.service.abstraction.PathService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PathServiceImpl implements PathService {
    private static final String DOT = ".";
    private static final String EMPTY_STRING = " ";

    @Override
    public String generateFileName(MultipartFile file) {
        if (file.getOriginalFilename() != null) {
            return UUID.randomUUID() + DOT + getFileExtension(file.getOriginalFilename());
        }
        return EMPTY_STRING;
    }

    @Override
    public String getFileExtension(String originalFilename) {
        if (originalFilename != null) {
            Pattern pattern = Pattern.compile("\\.(\\w+)$");
            Matcher matcher = pattern.matcher(originalFilename);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }
}
