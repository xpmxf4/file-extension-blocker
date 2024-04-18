package com.flow.fileextensionblocker.dto.request;

import com.flow.fileextensionblocker.domain.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.*;

@Getter
@ToString
public class CustomExtensionRequestDto {

    @Length(max = 20)
    @NotBlank
    private String name;

    public CustomExtension toEntity() {
        return CustomExtension.builder()
                .name(name)
                .build();
    }
}
