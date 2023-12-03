package com.bkizilkaya.culturelbackend.dto.request;

import com.bkizilkaya.culturelbackend.model.Image;
import lombok.*;

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
