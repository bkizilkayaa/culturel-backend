package com.bkizilkaya.culturelbackend.dto.artwork.request;

import com.bkizilkaya.culturelbackend.model.FileData;
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
@JsonPropertyOrder({"id", "title", "content", "description", "createDate", "modifiedDate", "zipCode", "authorId", "parentId", "fileData"})
@AllArgsConstructor
public class ArtworkCreateDTO {
    private Long Id;
    private String title;
    private String content;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private Long authorId;
    private Long parentId;
    private ZipCode zipCode;
    private List<FileData> fileData;
}
