package com.cloudpi.cloudpi_backend.files.permissions.entities;


import com.cloudpi.cloudpi_backend.files.filesystem.entities.FilesystemIdEntity;
import com.cloudpi.cloudpi_backend.files.permissions.pojo.FilePermissionType;
import com.cloudpi.cloudpi_backend.user.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_permissions")
public class FilePermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private FilePermissionType permissionType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fs_id")
    private FilesystemIdEntity file;
}