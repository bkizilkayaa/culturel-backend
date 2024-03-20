package com.bkizilkaya.culturelbackend.mapper;

import com.bkizilkaya.culturelbackend.dto.spot.request.TouristSpotCreateDTO;
import com.bkizilkaya.culturelbackend.dto.spot.response.TouristSpotResponseDTO;
import com.bkizilkaya.culturelbackend.model.TouristSpot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TouristSpotMapper {
    TouristSpotMapper INSTANCE = Mappers.getMapper(TouristSpotMapper.class);

    TouristSpotResponseDTO entityToResponseDto(TouristSpot touristSpot);

    TouristSpot dtoToEntity(TouristSpotCreateDTO touristSpotCreateDto);
}
