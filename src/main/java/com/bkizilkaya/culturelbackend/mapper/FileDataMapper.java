package com.bkizilkaya.culturelbackend.mapper;

import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.model.FileData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileDataMapper {
    FileDataMapper INSTANCE = Mappers.getMapper(FileDataMapper.class);

    FileDataResponseDTO fileDataToResponseDto(FileData fileData);

}
