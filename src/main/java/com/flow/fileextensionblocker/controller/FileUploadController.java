package com.flow.fileextensionblocker.controller;

import com.flow.fileextensionblocker.service.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;


@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService uploadService;

    @PostMapping("/api/files")
    public ResponseEntity<HttpStatus> uploadFile(MultipartFile file, HttpServletRequest request) {
        System.out.println("file = " + file);
        uploadService.uploadFile(file, request);
        return ResponseEntity.ok().build();
    }
}
