package com.example.mobileappws.security;

import com.example.mobileappws.io.entity.AuthorityEntity;
import com.example.mobileappws.io.entity.RoleEntity;
import com.example.mobileappws.io.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Erfan Akhavan Rad
 * @created 04/12/2023
 */
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -2953812971527002939L;
    UserEntity userEntity;
    private String userID;

    public UserPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.userID = userEntity.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
        Collection<GrantedAuthority> authorities = new HashSet<>();
//        List<AuthorityEntity> authorityEntities = new ArrayList<>();
        Collection<AuthorityEntity> authorityEntities = new HashSet<>();

        // Get User Roles
        Collection<RoleEntity> roles = userEntity.getRoles();

        if (roles == null) return authorities;

        roles.forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorityEntities.addAll(role.getAuthorities());
        });

        authorityEntities.forEach(authorityEntity -> {
            authorities.add(new SimpleGrantedAuthority(authorityEntity.getName()));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userEntity.getEmail();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.userEntity.getEmailVerificationStatus();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
