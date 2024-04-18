package com.flow.fileextensionblocker.service;

import com.flow.fileextensionblocker.domain.*;
import com.flow.fileextensionblocker.exception.*;
import com.flow.fileextensionblocker.repository.*;
import jakarta.servlet.http.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FileUploadService {

    private final UploadFileRepository uploadFileRepository;
    private final FileExtensionService extensionService;

    public void uploadFile(MultipartFile multipartFile, HttpServletRequest request) {
        // 업로드가 가능한 확장자인지 확인
        boolean isAllowedExtension = extensionService.isAllowedExtension(multipartFile);

        // 업로드가 가능한 확장자가 아니라면 예외발생
        if (!isAllowedExtension) throw new CustomException(ErrorCode.IS_NOT_ALLOWED_EXTENSION);

        // 파일을 업로드할 경로를 생성 -> 상대경로를 이용
        String uploadPath = System.getProperty("user.dir").concat("/files");

        // 파일의 확장자를 얻어온다.
        String extension = extensionService.getExtension(multipartFile);

        // 파일의 이름을 설정
        String fileName = UUID.randomUUID() + "." + extensionService.getExtension(multipartFile);

        try {
            // 파일 객체를 만들고 디렉토리에 파일을 생성한다.
            File uploadFile = new File(uploadPath, fileName);
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            log.error("error = {}", e);
            // IOException은 Checked Exception 이므로 IOException 대신 커스텀 예외를 던져준다.
            throw new CustomException(ErrorCode.FILE_SAVE_ERROR_INTERNAL_SERVER_ERROR);
        }

        // 파일 저장 후, 파일에 대한 정보를 담은 엔티티를 생성
        UploadedFile file = UploadedFile.builder()
                .filename(fileName)
                .path(uploadPath)
                .extension(extension)
                .build();

        uploadFileRepository.save(file);
    }
}
