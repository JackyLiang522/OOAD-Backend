package com.sustech.ooad.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private static final String VIDEO_FOLDER = "files/video/";
    private static final String PDF_FOLDER = "files/pdf/";
    private static final String IMAGE_FOLDER = "files/image/";

    private static final String STUDENT_PDF = "files/studentAssignment/";

    // 视频上传
    @PostMapping("/video")
    public String singleVideoUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("chapterId") Long chapterId) {
        try {
            // Save the uploaded file to the specified directory
            byte[] bytes = file.getBytes();
            String filename = VIDEO_FOLDER + chapterId + ".mp4";
            File uploadedFile = new File(filename);
            uploadedFile.createNewFile();
            java.nio.file.Files.write(uploadedFile.toPath(), bytes);
            return filename;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }


    // Set up the endpoint for retrieving stored videos
    @GetMapping("/video/{filename:.+}")
    public ResponseEntity<byte[]> getVideo(@PathVariable String filename) {
        return getFileUtil(filename, VIDEO_FOLDER);
    }

    // 教师上传作业附件
    @PostMapping("/pdf")
    public String singlePDFUpload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("chapterId") Long chapterId) {
        try {
            // Save the uploaded file to the specified directory
            byte[] bytes = file.getBytes();
            String filename = PDF_FOLDER + chapterId + ".pdf";
            File uploadedFile = new File(filename);
            uploadedFile.createNewFile();
            java.nio.file.Files.write(uploadedFile.toPath(), bytes);
            return filename;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }

    // Set up the endpoint for retrieving stored videos
    @GetMapping("/pdf/{filename:.+}")
    public ResponseEntity<byte[]> getPDF(@PathVariable String filename) {
        return getFileUtil(filename, PDF_FOLDER);
    }


    @PostMapping("/studentAssignment")
    public String assignmentUpload(@RequestParam("file") MultipartFile file,
                                  @RequestParam("chapterId") Long chapterId,
                                  @RequestParam("studentId") Long studentId) {
        try {
            // Save the uploaded file to the specified directory
            byte[] bytes = file.getBytes();
            String filename = PDF_FOLDER + chapterId + "_" + studentId +".pdf";
            File uploadedFile = new File(filename);
            uploadedFile.createNewFile();
            java.nio.file.Files.write(uploadedFile.toPath(), bytes);
            return filename;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }

    @GetMapping("/pdf/{filename:.+}")
    public ResponseEntity<byte[]> getAssignmentPDF(@PathVariable String filename) {
        return getFileUtil(filename, STUDENT_PDF);
    }

    private ResponseEntity<byte[]> getFileUtil(@PathVariable String filename, String studentPdf) {
        try {
            // Retrieve the file from the specified directory
            Path path = Paths.get(studentPdf + filename);
            byte[] pdf = Files.readAllBytes(path);

            // Return the video as the response body
            return new ResponseEntity<>(pdf, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 上传课程封面
    @PostMapping("/image")
    public String singleImageUpload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("courseId") Long courseId) {
        try {
            // Save the uploaded file to the specified directory
            byte[] bytes = file.getBytes();
            String fileExtension = file.getOriginalFilename().split("\\.")[1];
            String filename = IMAGE_FOLDER + courseId + fileExtension;
            File uploadedFile = new File(filename);
            uploadedFile.createNewFile();
            java.nio.file.Files.write(uploadedFile.toPath(), bytes);
            return filename;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }

    // Set up the endpoint for retrieving stored videos
    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        return getFileUtil(filename, IMAGE_FOLDER);
    }
}
