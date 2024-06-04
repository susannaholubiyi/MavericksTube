package com.maverickstube.maverickshub.repositories;

import com.maverickstube.maverickshub.models.Media;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
