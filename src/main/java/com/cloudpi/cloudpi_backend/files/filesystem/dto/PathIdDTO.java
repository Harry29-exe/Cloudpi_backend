package com.cloudpi.cloudpi_backend.files.filesystem.dto;

public class PathIdDTO {

    private final String id;
    private final String parentId;

    public PathIdDTO(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getParentId() {
        return parentId;
    }

//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//    }
}
//public interface PathIdDTO {
//
//    String getId();
//    String getParentId();
//
//}
