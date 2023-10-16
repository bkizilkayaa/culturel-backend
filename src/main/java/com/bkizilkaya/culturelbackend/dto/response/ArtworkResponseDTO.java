package com.bkizilkaya.culturelbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtworkResponseDTO {
    private String title;
    private String content;
    private Date createDate;
    private Double coordinateX;
    private Double coordinateY;
    private Long authorId;
}
