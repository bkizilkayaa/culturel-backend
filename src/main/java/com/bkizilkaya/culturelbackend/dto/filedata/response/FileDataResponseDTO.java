package com.bkizilkaya.culturelbackend.dto.filedata.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDataResponseDTO {
    private Long Id;
    private String name;
    private String type;
}
