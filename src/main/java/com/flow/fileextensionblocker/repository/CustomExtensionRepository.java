package com.flow.fileextensionblocker.repository;

import com.flow.fileextensionblocker.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {

    // 차단된 파일 확장자인지 확인하거나 이미 있는 파일 확장자인지 필터링하기 위한 메서드
    boolean existsByName(String name);

    Optional<CustomExtension> findByName(String name);
}
