package com.bkizilkaya.culturelbackend.dto.filedata.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDataCreateRequestDTO {
    private Long ID;
    private String name;
    private String type;

}
