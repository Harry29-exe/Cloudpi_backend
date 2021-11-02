package com.cloudpi.cloudpi_backend.files.filesystem.enpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.checkerframework.checker.units.qual.A;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;

import java.util.List;

@Tag(name = "File API", description =
        "API for uploading, downloading, deleting files and directories")
public interface FileApiDocs extends FileAPI {

    @Operation(
            summary = "Image uploads",
            description = """
                    <h1>Not implemented</h1>
                    Allows to upload image to server. Endpoint behave similar to POST file/{filePath}
                    but this method use user .images folder instead of precisely defined path.
                    """,
            parameters = @Parameter(name = "imageName", description = "Name with extension of image to upload", example = "funny-cat.jpg"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "success"),
                    @ApiResponse(responseCode = "400", description = "when request does not have body or image name does not contain file extension"),
                    @ApiResponse(responseCode = "403", description = "when user does not have permission to save image e.g. not enough space"),
                    @ApiResponse(responseCode = "409", description = "when image with that name already exist")
            }
    )
    void uploadNewImage(String imageName, byte[] image, Authentication auth);

    @Operation(
            summary = "returns requested images in reduced resolution",
            description = "",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "403")
            }
    )
    @Override
    List<Resource> getImagesPreview(Integer previewResolution, String imageFormat, List<String> imageNames);

    @Operation(
            summary = "Uploads file",
            description = """
                    Allows to upload file to server. Based on filePath server check current user permissions
                    and if user has rights to save file then request body is saved to disc.
                    """,
            responses = {
                    @ApiResponse(responseCode = "201", description = "success"),
                    @ApiResponse(responseCode = "400", description = "when file path is invalid"),
                    @ApiResponse(responseCode = "409", description = "when file with given filepath already exist")
            }
    )
    @Override
    void uploadNewFile(String filePath, byte[] file);


    @Operation(
            summary = "Forces file upload",
            description = """
                    Allows to upload file to server. Based on filePath server check current user permissions
                    and if user has rights to save file then request body is saved to disc. Unlike Post method
                    PUT overrides existing files.
                    """,
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400", description = "bad path")
            })
    @Override
    void forceUploadNewFile(String filePath, byte[] file);

    @Operation(
            summary = "Creates empty directory",
            description = """
                    
                    """
    )
    @Override
    void createDirectory(String directoryPath);


    @Operation(
            summary = "Downloads file",
            description = """
                    Downloads file with given id.
                    """)
    @Override
    byte[] downloadFile(Long fileId);


    @Operation(
            summary = "Compresses directory and downloads it",
            description = """
                    Compress directory to .7z and sends it in response body.
                    """)
    @Override
    byte[] compressAndDownloadDirectory(Long directoryId);

    @Operation(
            summary = "override standard description"
    )
    @Override
    void deleteFile(Long fileId);


    @Operation(
            summary = "deletes empty directory"
    )
    @Override
    void deleteDirectory(Long directoryId);


    @Operation(
            summary = "forces to delete directory",
            description = "Deletes directory whether there are files in it or not"
    )
    @Override
    void forceDeleteDirectory(Long directoryId);
}
