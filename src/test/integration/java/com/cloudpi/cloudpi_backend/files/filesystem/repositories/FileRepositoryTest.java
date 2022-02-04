package com.cloudpi.cloudpi_backend.files.filesystem.repositories;

import com.cloudpi.cloudpi_backend.exepctions.files.PathNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class FileRepositoryTest {

    @Autowired
    FileRepo fileRepo;

    //    @Test
    void findDtoById() {
        var file = fileRepo.findAll().get(0);
        var dto = fileRepo.findDtoById(file.getId())
                .orElseThrow(PathNotFoundException::noSuchFile);
        assert dto.getId().equals(file.getId());
    }

    //    @Test
    void findDtoByPath() {
    }
}