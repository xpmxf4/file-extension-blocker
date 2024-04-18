package com.flow.fileextensionblocker.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "UPLOADED_FILE")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadedFile {

    @Id @GeneratedValue
    private Long id;

    private String filename;
    private String path;
    private String extension;
}
