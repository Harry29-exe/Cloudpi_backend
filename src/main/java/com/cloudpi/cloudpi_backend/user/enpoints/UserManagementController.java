package com.cloudpi.cloudpi_backend.user.enpoints;

import com.cloudpi.cloudpi_backend.exepctions.user.endpoint.NoSuchUserException;
import com.cloudpi.cloudpi_backend.user.dto.AccountType;
import com.cloudpi.cloudpi_backend.user.dto.CreateUserVal;
import com.cloudpi.cloudpi_backend.user.dto.UpdateUserVal;
import com.cloudpi.cloudpi_backend.user.requests.PostUserRequest;
import com.cloudpi.cloudpi_backend.user.requests.UpdateUserDetailsRequest;
import com.cloudpi.cloudpi_backend.user.responses.GetUserResponse;
import com.cloudpi.cloudpi_backend.user.responses.GetUserWithDetailsResponse;
import com.cloudpi.cloudpi_backend.user.services.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserManagementController implements UserManagementAPI {
    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }


    @Override
    public List<GetUserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(GetUserResponse::fromUserPublicId)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetUserWithDetailsResponse> getAllUsersWithDetails() {
        return userService
                .getAllUsersWithDetails()
                .stream()
                .map(GetUserWithDetailsResponse::from)
                .toList();
    }

    @Override
    public GetUserWithDetailsResponse getUserDetails(String username) {
        var userWithDetails = userService.getUserDetails(username)
                .orElseThrow(NoSuchUserException::notFoundByUsername);

        return GetUserWithDetailsResponse.from(userWithDetails);
    }

    @Override
    public void createNewUser(PostUserRequest user) {
       //TODO validate PostUserRequest
        var createUser = new CreateUserVal(
                user.getUsername(),
                user.getPassword(),
                user.getNickname(),
                user.getAccountType() != null?
                    user.getAccountType():
                    AccountType.USER,
                user.getEmail()
        );
        userService.createUserWithDefaultAuthorities(createUser);
    }

    @Override
    public void updateUserDetails(String username, UpdateUserDetailsRequest request) {
        userService.updateUser(username, new UpdateUserVal(
                request.getNickname(),
                null,
                request.getEmail(),
                request.getPathToProfilePicture()));
    }

    @Override
    public void scheduleUserDelete(String nickname) {
        userService.scheduleUserDeleting(nickname);
    }

    @Override
    public void deleteUser(String nickname) {
        userService.deleteUser(nickname);
    }
}