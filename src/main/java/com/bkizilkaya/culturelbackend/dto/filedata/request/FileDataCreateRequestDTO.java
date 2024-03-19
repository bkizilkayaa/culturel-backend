package com.bkizilkaya.culturelbackend.dto.filedata.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDataCreateRequestDTO {
    private MultipartFile multipartFile;
}
