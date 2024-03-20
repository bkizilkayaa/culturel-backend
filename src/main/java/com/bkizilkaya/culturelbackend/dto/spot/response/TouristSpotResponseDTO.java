package com.bkizilkaya.culturelbackend.dto.spot.response;

import com.bkizilkaya.culturelbackend.dto.filedata.response.FileDataResponseDTO;
import com.bkizilkaya.culturelbackend.model.ZipCode;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "title", "content", "description", "createDate", "modifiedDate", "zipCode", "authorId", "parentId", "fileData"})
public class TouristSpotResponseDTO {
    private Long Id;
    private String title;
    private String content;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private Long authorId;
    private ZipCode zipCode;
    private List<FileDataResponseDTO> fileData;
    private Long parentId;
}
