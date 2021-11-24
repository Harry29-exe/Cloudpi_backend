package com.cloudpi.cloudpi_backend.files.filesystem.repositories;

import com.cloudpi.cloudpi_backend.files.filesystem.dto.FileDto;
import com.cloudpi.cloudpi_backend.files.filesystem.dto.FileDetailsDto;
import com.cloudpi.cloudpi_backend.files.filesystem.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {

    List<FileDetailsDto> findAllDetailsByIdIn(Iterable<UUID> ids);

    List<FileDto> findAllDtoByIdIn(Iterable<UUID> ids);

    List<FileDto> findAllDtoByPathIn(Iterable<String> paths);

    Optional<FileEntity> findByPath(String path);

    Optional<FileDto> findDtoById(UUID id);

    Optional<FileDto> findDtoByPath(String path);

    Boolean existsByPath(String path);

    void deleteByPath(String path);

    @Transactional
    @Modifying
    @Query("""
            UPDATE files f
            SET f.size = :newSize,
                f.modifiedAt = updateDate
            WHERE f.id = :id
            """)
    void updateFileSize(UUID id, Long newSize, Date updateDate);

}