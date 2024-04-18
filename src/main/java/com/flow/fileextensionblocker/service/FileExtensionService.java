package com.flow.fileextensionblocker.service;

import com.flow.fileextensionblocker.domain.*;
import com.flow.fileextensionblocker.dto.request.*;
import com.flow.fileextensionblocker.dto.response.*;
import com.flow.fileextensionblocker.exception.*;
import com.flow.fileextensionblocker.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import java.util.*;
import java.util.stream.*;

import static com.flow.fileextensionblocker.exception.ErrorCode.*;


@Service
@Transactional
@RequiredArgsConstructor
public class FileExtensionService {

    private final CustomExtensionRepository customExtensionRepository;
    private final DefaultExtensionRepository defaultExtensionRepository;


    // 새로운 커스텀 확장자를 저장
    public void addCustomExtension(CustomExtensionRequestDto customExtensionDto) {

        // 커스텀 확장자와 기본 확장자에 추가하려는 확장자가 있는지 조회한다.
        boolean isDuplicatedDefaultExtension = customExtensionRepository.existsByName(customExtensionDto.getName());
        boolean isDuplicatedCustomExtension = defaultExtensionRepository.existsByName(customExtensionDto.getName());

        // 커스텀 확장자의 현재 개수를 센다 -> 200개 초과일경우 저장불가
        long customExtensionCount = customExtensionRepository.count();

        // 커스텀 확장자와 기본 확장자 중 한 곳에라도 있는 확장자면 추가 불가
        if (isDuplicatedCustomExtension || isDuplicatedDefaultExtension) {
            throw new CustomException(IS_DUPLICATE_BAD_REQUEST);
        }

        // 커스텀 확장자의 개수가 200를 초과되지 못하도록 방어
        if (customExtensionCount >= 200) {
            throw new CustomException(NOT_LEFT_SPACE_TO_SAVE_EXTENSION_BAD_REQUEST);
        }

        customExtensionRepository.save(customExtensionDto.toEntity());
    }

    // 커스텀 확장자 삭제
    public void removeCustomExtension(String name) {
        CustomExtension extensionDto = customExtensionRepository.findByName(name)
                .orElseThrow(() -> new CustomException(IS_NOT_PRESENT_EXTENSION_BAD_REQUEST));

        customExtensionRepository.delete(extensionDto);
    }

    // 디폴트 확장자의 체크 여부를 변경
    public void modifyDefaultExtension(String name) {
        DefaultExtension defaultExtension = defaultExtensionRepository.findByName(name)
                .orElseThrow(() -> new CustomException(IS_NOT_PRESENT_EXTENSION_BAD_REQUEST));

        // 디폴트 확장자가 체크 되어 있다면 -> 체크되지 않은 상태로 변경
        if (defaultExtension.isChecked()) {
            defaultExtensionRepository.updateDefaultExtensionUnchecked(name);
            return;
        }

        // 디폴트 확장자가 체크 되어 있지 않다면 -> 체크된 상태로 변
        defaultExtensionRepository.updateDefaultExtensionChecked(name);
    }

    @Transactional(readOnly = true)
    // 파일의 확장자가 등록가능한 확장자인지 확인
    public boolean isAllowedExtension(MultipartFile multipartFile) {
        String extension = getExtension(multipartFile);

        // 커스텀 확장자에 존재하지 않고, 기본 확장자는 체크 상태일 경우 업로드가 불가능하다.
        boolean isAllowedDefaultExtension = defaultExtensionRepository.existsByNameAndIsChecked(extension, true);
        boolean isAllowedCustomExtension = customExtensionRepository.existsByName(extension);

        return !isAllowedDefaultExtension && !isAllowedCustomExtension;
    }

    // 기본 확장자 리스트를 가져오는 메서드
    public List<DefaultExtensionResponseDto> findAllDefaultExtension() {
        return defaultExtensionRepository.findAll()
                .stream()
                .map(DefaultExtensionResponseDto::new)
                .collect(Collectors.toList());
    }

    // 커스텀 확장자를 가져오는 메서드
    public List<CustomExtensionResponseDto> findAllCustomExtension() {
        return customExtensionRepository.findAll()
                .stream()
                .map(CustomExtensionResponseDto::new)
                .collect(Collectors.toList());
    }

    // 파일의 확장자만 뽑아오는 메서드
    public String getExtension(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename()
                .substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
    }


    // 기본 확장자 7개의 데이터를 저장하는 메서드 -> 서버가 시작할때 등록
    public void setDefaultData() {
        String[] defaultExtensionArr = new String[] {
                "bat", "cmd", "com", "cpl", "exe", "scr", "js"
        };

        for (String name : defaultExtensionArr) {
            DefaultExtension extension = DefaultExtension.builder()
                    .name(name)
                    .isChecked(false)
                    .build();

            defaultExtensionRepository.save(extension);
        }
    }
}
