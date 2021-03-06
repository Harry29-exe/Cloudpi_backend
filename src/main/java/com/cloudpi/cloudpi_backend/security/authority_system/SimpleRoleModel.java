package com.cloudpi.cloudpi_backend.security.authority_system;

import com.cloudpi.cloudpi_backend.authorities.dto.AuthorityDTO;
import com.cloudpi.cloudpi_backend.authorities.dto.AuthorityType;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

//TODO
public class SimpleRoleModel implements RoleModel {
    private final String roleName;
    private final AuthorityDTO authorityDTO;
    private final GrantedAuthority roleAuthority;
    private final ImmutableCollection<PermissionModel> rolesPermissions;
    private final ImmutableCollection<GrantedAuthority> permissionAuthorities;
    private final ImmutableCollection<GrantedAuthority> authorities;
    private final ImmutableCollection<GrantedAuthority> mayBeGivenBy;
    private final ImmutableCollection<String> haveItByDefault;

    public SimpleRoleModel(String roleName,
                           ImmutableCollection<PermissionModel> rolesPermissions,
                           ImmutableCollection<String> mayBeGivenBy,
                           ImmutableCollection<String> haveItByDefault) {
        this.roleName = roleName;
        this.authorityDTO = new AuthorityDTO(AuthorityType.ROLE, roleName);
        this.roleAuthority = new SimpleGrantedAuthority(roleName);
        this.rolesPermissions = rolesPermissions;
        this.mayBeGivenBy = ImmutableList.copyOf(mayBeGivenBy.stream().map(SimpleGrantedAuthority::new).toList());
        this.haveItByDefault = haveItByDefault;
        this.permissionAuthorities = ImmutableList.copyOf(
                rolesPermissions
                        .stream()
                        .map(PermissionModel::getAuthority).toList()
        );

        var authorityList = new ArrayList<>(permissionAuthorities);
        authorityList.add(roleAuthority);
        this.authorities = ImmutableList.copyOf(authorityList);

    }

    @Override
    public ImmutableCollection<String> getAccountsThatHaveItByDefault() {
        return haveItByDefault;
    }

    @Override
    public ImmutableCollection<PermissionModel> getPermissionModels() {
        return rolesPermissions;
    }

    @Override
    public ImmutableCollection<GrantedAuthority> getRolesPermissions() {
        return this.permissionAuthorities;
    }

    @Override
    public GrantedAuthority getRole() {
        return roleAuthority;
    }

    @Override
    public ImmutableCollection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getAuthorityName() {
        return roleName;
    }

    @Override
    public ImmutableCollection<GrantedAuthority> mayBeGivenBy() {
        return mayBeGivenBy;
    }

    @Override
    public int compareTo(RoleModel o) {
        return this.roleName.compareTo(o.getAuthorityName());
    }

    @Override
    public AuthorityDTO toAuthorityDTO() {
        return authorityDTO;
    }
}
