package com.cloudpi.cloudpi_backend.user.controllers;

import com.cloudpi.cloudpi_backend.configuration.network.LocalNetworksInfo;
import com.cloudpi.cloudpi_backend.user.dto.UserDTO;
import com.cloudpi.cloudpi_backend.user.mappers.UserMapper;
import com.cloudpi.cloudpi_backend.user.repositories.UserRepository;
import com.cloudpi.cloudpi_backend.user.requests.PostUserRequest;
import com.cloudpi.cloudpi_backend.user.requests.UpdateUserDetailsRequest;
import com.cloudpi.cloudpi_backend.user.responses.GetUserResponse;
import com.cloudpi.cloudpi_backend.user.responses.GetUsersResponse;
import com.cloudpi.cloudpi_backend.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-management/")
public class UserManagementController implements UserManagementAPI {
    private final UserRepository userRepository;
    private final UserService userService;
    private final LocalNetworksInfo networksInfo;

    public UserManagementController(UserService userService,
                                    LocalNetworksInfo networksInfo,
                                    UserRepository userRepository) {
        this.userService = userService;
        this.networksInfo = networksInfo;
        this.userRepository = userRepository;
    }


    @Override
    public List<GetUserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserMapper.INSTANCE::userDTOToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GetUserResponse getUser(String username) {
        return null;
    }

    @Override
    public void createNewUser(PostUserRequest user) {

    }

    @Override
    public void updateUserDetails(String username, UpdateUserDetailsRequest request) {

    }

    @Override
    public void scheduleUserDelete(String name) {

    }

    @Override
    public void deleteUser(String name) {
        System.out.println("we1");
        userService.updateUserDetails(new UserDTO());
    }
}