package com.bkizilkaya.culturelbackend.utils;

import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.model.FileData;

public class FileDataMapper {
    public static FileDataResponseDTO fileDataMapperForResponseDto(FileData fileData) {
        return FileDataResponseDTO.builder().ID(fileData.getID())
                .type(fileData.getType())
                .name(fileData.getName())
                .build();
    }
}
