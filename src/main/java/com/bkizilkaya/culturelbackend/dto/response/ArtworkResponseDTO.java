package com.bkizilkaya.culturelbackend.dto.response;

import com.bkizilkaya.culturelbackend.model.Image;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtworkResponseDTO {
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Long authorId;
    private Integer zipCode;
    private List<Image> images;
}
