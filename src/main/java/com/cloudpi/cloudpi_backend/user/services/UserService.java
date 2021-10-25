package com.cloudpi.cloudpi_backend.user.services;

import com.cloudpi.cloudpi_backend.authorities.dto.AuthorityDTO;
import com.cloudpi.cloudpi_backend.user.dto.UserDetailsDTO;
import com.cloudpi.cloudpi_backend.user.dto.UserPublicIdDTO;
import com.cloudpi.cloudpi_backend.user.dto.UserWithDetailsDTO;
import com.cloudpi.cloudpi_backend.user.requests.PostUserRequest;
import com.google.common.collect.ImmutableList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

import static com.cloudpi.cloudpi_backend.user.enpoints.UserAPIAuthorities.*;

public interface UserService {

    @PreAuthorize("isAuthenticated()")
    ImmutableList<UserPublicIdDTO> getAllUsers();

    @Secured(GET_DETAILS)
    Optional<UserWithDetailsDTO> getUserDetails(String username);

//    @PreAuthorize("hasAuthority('"+GET_DETAILS+"')")
    @Secured(GET_DETAILS)
    List<UserWithDetailsDTO> getAllUsersWithDetails();

    /**
     *
     * @param user the user to be created
     * @return the authorities that could not be added to user
     * because creator of the user doesn't have rights to give them
     */
//    @PreAuthorize("hasAuthority('"+CREATE+"')")
    @Secured(CREATE)
    List<AuthorityDTO> createUserWithDefaultAuthorities(UserWithDetailsDTO user, String nonEncodedPassword);

    @PreAuthorize(
            "hasAuthority("+MODIFY+") or " +
            "#userDTO.login == principal"
    )
    void updateUserDetails(String nickname, UserDetailsDTO userDetails);

    @Secured(LOCK)
    void lockUser(UserPublicIdDTO user);

    @Secured(LOCK)
    void lockUser(String nickname);

    @PreAuthorize(
            "hasAuthority("+DELETE+") or "+
            "#user.login == principal"
    )
    void deleteUser(String nickname);

    @PreAuthorize(
            "hasAuthority("+SCHEDULE_DELETE+") or "+
            "#user.login = principal"
    )
    void scheduleUserDeleting(String nickname);

}