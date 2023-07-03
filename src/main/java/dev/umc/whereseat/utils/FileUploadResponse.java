package dev.umc.whereseat.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {

    private String fileUrl;
    private String filePath;
}
