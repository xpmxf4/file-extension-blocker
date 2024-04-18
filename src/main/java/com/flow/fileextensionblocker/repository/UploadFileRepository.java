package com.flow.fileextensionblocker.repository;

import com.flow.fileextensionblocker.domain.*;
import org.springframework.data.jpa.repository.*;

public interface UploadFileRepository extends JpaRepository<UploadedFile, Long> {

}
