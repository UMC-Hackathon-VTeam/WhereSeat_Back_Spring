package dev.umc.whereseat.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileUploadUtil fileUploadUtil;

    @PostMapping("/test/file/upload")
    public FileUploadResponse uploadFile(@RequestParam("category") String category, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return fileUploadUtil.uploadFile(category, multipartFile);
    }
}
