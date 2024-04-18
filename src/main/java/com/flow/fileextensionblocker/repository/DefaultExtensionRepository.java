package com.flow.fileextensionblocker.repository;


import com.flow.fileextensionblocker.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface DefaultExtensionRepository extends JpaRepository<DefaultExtension, Long> {

    @Query("update DefaultExtension d set d.isChecked = true where d.name = :name")
    @Modifying(clearAutomatically = true)
    void updateDefaultExtensionChecked(String name);

    @Query("update DefaultExtension d set d.isChecked = false where d.name = :name")
    @Modifying(clearAutomatically = true)
    void updateDefaultExtensionUnchecked(String name);

    // 커스텀 확장자에서 이미 있는 디폴트 확장자를 추가 하지 않도록 검증할때 사용
    boolean existsByName(String name);

    boolean existsByNameAndIsChecked(String name, boolean isChecked);

    Optional<DefaultExtension> findByName(String name);
}
