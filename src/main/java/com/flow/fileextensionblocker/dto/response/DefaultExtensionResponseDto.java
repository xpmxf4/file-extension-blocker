package com.flow.fileextensionblocker.dto.response;

import com.flow.fileextensionblocker.domain.*;
import lombok.*;

@ToString
@Getter
public class DefaultExtensionResponseDto {

    String name;
    boolean isChecked;

    public DefaultExtensionResponseDto(DefaultExtension extension) {
        this.name = extension.getName();
        this.isChecked = extension.isChecked();
    }
}
