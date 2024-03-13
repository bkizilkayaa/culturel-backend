package com.bkizilkaya.culturelbackend.dto.filedata.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDataListResponseDTO {
    private int totalCount;
    private List<FileDataResponseDTO> fileDataResponeDTOList = new ArrayList<>();
}