package com.flow.fileextensionblocker.dto.response;


import com.flow.fileextensionblocker.domain.*;
import lombok.*;

@Getter
@ToString
public class CustomExtensionResponseDto {
    String name;

    public CustomExtensionResponseDto(CustomExtension customExtension) {
        this.name = customExtension.getName();
    }
}
