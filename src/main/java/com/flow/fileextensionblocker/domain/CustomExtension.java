package com.flow.fileextensionblocker.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "CUSTOM_EXTENSION")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomExtension {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public String getName() {
        return name;
    }
}
