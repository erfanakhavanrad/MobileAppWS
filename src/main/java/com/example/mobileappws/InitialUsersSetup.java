package com.example.mobileappws;

import com.example.mobileappws.io.entity.AuthorityEntity;
import com.example.mobileappws.io.entity.RoleEntity;
import com.example.mobileappws.io.entity.UserEntity;
import com.example.mobileappws.io.repository.AuthorityRepository;
import com.example.mobileappws.io.repository.RoleRepository;
import com.example.mobileappws.io.repository.UserRepository;
import com.example.mobileappws.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Erfan Akhavan Rad
 * @created 04/02/2023
 */
@Component
public class InitialUsersSetup {

    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    Utils utils;
    @Autowired
    UserRepository userRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("From Application Ready Event");

        AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
        AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
        AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

        RoleEntity roleUser = createRole("ROLE_USER", Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole("ROLE_ADMIN", Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        if (roleAdmin == null) return;

        UserEntity adminUser = new UserEntity();
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        adminUser.setEmail("admin@email.com");
        adminUser.setEmailVerificationStatus(true);
        adminUser.setUserId(utils.generateUserId(30));
        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("12345678"));
        adminUser.setRoles(Arrays.asList(roleAdmin));
        userRepository.save(adminUser);


    }

    @Transactional
    private AuthorityEntity createAuthority(String name) {
        AuthorityEntity authorityEntity = authorityRepository.findByName(name);
        if (authorityEntity == null) {
            authorityEntity = new AuthorityEntity(name);
            authorityRepository.save(authorityEntity);
        }
        return authorityEntity;
    }

    @Transactional
    private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {
        RoleEntity role = roleRepository.findByName(name);
        if (role == null) {
            role = new RoleEntity(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }
        return role;
    }


}
