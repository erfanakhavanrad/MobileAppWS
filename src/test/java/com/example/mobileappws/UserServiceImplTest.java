package com.example.mobileappws;

import com.example.mobileappws.io.entity.UserEntity;
import com.example.mobileappws.io.repository.UserRepository;
import com.example.mobileappws.service.impl.UserServiceImpl;
import com.example.mobileappws.shared.Utils;
import com.example.mobileappws.shared.dto.AddressDTO;
import com.example.mobileappws.shared.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String userID = "httpsek";
    String encryptedPassword = "grk4e3rDK#";
    UserEntity userEntity;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Sergey");
        userEntity.setUserId(userID);
        userEntity.setEncryptedPassword(encryptedPassword);
        userEntity.setEmail("test@test.com");
        userEntity.setEmailVerificationToken("8dg4dgredg22ad");
    }

    @Test
    final void testGetUser() {
//        fail("Not yet implemented");


        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        UserDto userDto = userService.getUser("test@test.com");

        assertNotNull(userDto);
        assertEquals("Sergey", userDto.getFirstName());

    }

    @Test
    final void testGetUser_UsernameNotFoundException() {

        when(userRepository.findByEmail(anyString())).thenReturn(null);

//        userService.getUser("test@test.com");

        assertThrows(UsernameNotFoundException.class,
                () -> {
                    userService.getUser("t,eawdwadst@test.com");
                }
        );
    }

    @Test
    final void testCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateAddressId(anyInt())).thenReturn("htfghthm");
        when(utils.generateUserId(anyInt())).thenReturn(userID);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setType("Shipping");

        List<AddressDTO> addresses = new ArrayList<>();
        addresses.add(addressDTO);

        UserDto userDto = new UserDto();
        userDto.setAddresses(addresses);
        UserDto storedUserDetails = userService.createUser(userDto);
        assertNotNull(storedUserDetails);
        assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());

    }

}
