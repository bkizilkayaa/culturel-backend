package com.bkizilkaya.culturelbackend.utils;

import com.bkizilkaya.culturelbackend.model.FileData;
import com.bkizilkaya.culturelbackend.service.concrete.FileDataServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@Component
public class ImageValidator {
    private static final Integer NO_HEIGHT = 0;
    private static final Integer NO_WIDTH = 0;

    public boolean isImage(MultipartFile file) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            return checkDimensionsOfImage(image);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkDimensionsOfImage(BufferedImage image) {
        return image.getHeight() > NO_HEIGHT && image.getWidth() > NO_WIDTH;
    }

}
