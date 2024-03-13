package com.bkizilkaya.culturelbackend.dto.artwork.request;

import com.bkizilkaya.culturelbackend.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtworkCreateDTO {
    private Long Id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Long authorId;
    private Integer zipCode;
    private List<Image> images;
    private Long parentId;
    private Long imageId; // Yeni eklenen alan

}