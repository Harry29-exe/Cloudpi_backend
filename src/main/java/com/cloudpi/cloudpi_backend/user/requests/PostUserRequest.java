package com.cloudpi.cloudpi_backend.user.requests;

import com.cloudpi.cloudpi_backend.user.dto.AccountType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class PostUserRequest {

    @NotBlank
    private String nickname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Nullable
    private AccountType accountType = AccountType.USER;
    @Nullable
    private String email;
    //TODO flaga administable

}