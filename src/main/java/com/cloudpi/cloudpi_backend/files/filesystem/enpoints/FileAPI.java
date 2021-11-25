package com.cloudpi.cloudpi_backend.files.filesystem.enpoints;

import com.cloudpi.cloudpi_backend.files.filesystem.pojo.FileType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("/files/")
@Tag(name = "File API",
        description = "API for uploading and downloading files")
public interface FileAPI {


    @PostMapping(
            path = "image/{imageName}",
            consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void uploadNewImage(
            @PathVariable String imageName,
            @RequestBody byte[] image,
            Authentication auth);


    @GetMapping(path = "image-preview")
    List<Resource> getImagesPreview(
            @RequestParam(defaultValue = "64") Integer previewResolution,
            @RequestBody List<String> imageNames);


    @PostMapping(
            path = "file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    void uploadNewFile(
            @RequestParam(defaultValue = "UNDEFINED") FileType fileType,
            @RequestParam String filepath,
            @RequestParam MultipartFile file,
            Authentication auth);


    @PutMapping(
            path = "file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void forceUploadNewFile(
            @RequestParam(defaultValue = "UNDEFINED") FileType fileType,
            @RequestParam String filepath,
            @RequestBody MultipartFile file,
            Authentication auth);


//    @PatchMapping("file/move")
//    void moveFile(@PathVariable )


    @GetMapping("file/{fileId}")
    Resource downloadFile(@PathVariable String fileId);


    @DeleteMapping("file/{fileId}")
    void deleteFile(@PathVariable String fileId);


    @DeleteMapping(
            path = "file",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void deleteFiles(@RequestBody @NonNull List<String> fileId);

}