package com.flow.fileextensionblocker.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "DEFAULT_EXTENSION")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultExtension {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private boolean isChecked;

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
