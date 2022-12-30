package com.sustech.ooad.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private static final String VIDEO_FOLDER = "files/video/";

    @PostMapping("/video")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded file to the specified directory
            byte[] bytes = file.getBytes();
            File uploadedFile = new File(VIDEO_FOLDER + file.getOriginalFilename());
            uploadedFile.createNewFile();
            java.nio.file.Files.write(uploadedFile.toPath(), bytes);
            return VIDEO_FOLDER + file.getOriginalFilename();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }
}
