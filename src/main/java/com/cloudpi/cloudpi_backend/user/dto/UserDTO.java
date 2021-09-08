package com.cloudpi.cloudpi_backend.user.dto;

import com.cloudpi.cloudpi_backend.security.CloudPIUser;
import com.cloudpi.cloudpi_backend.security.permissions.AccountType;

import com.cloudpi.cloudpi_backend.user.entities.UserEntity;
import com.cloudpi.cloudpi_backend.user.mappers.UserMapper;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String nickname;
    private String password;
    private Boolean locked;
    private AccountType accountType;
    @EqualsAndHashCode.Exclude
    private List<GrantedAuthority> permissions;

    public UserEntity toUserEntity() {
        return UserMapper.INSTANCE.userDTOToUserEntity(this);
    }

    public CloudPIUser toCloudPiUser() {
        return new CloudPIUser(this);
    }
}