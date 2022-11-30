package com.example.mobileappws;

import com.example.mobileappws.exceptions.UserServiceException;
import com.example.mobileappws.io.entity.AddressEntity;
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
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        userEntity.setLastName("Kasporov");
        userEntity.setUserId(userID);
        userEntity.setEncryptedPassword(encryptedPassword);
        userEntity.setEmail("test@test.com");
        userEntity.setEmailVerificationToken("8dg4dgredg22ad");
        userEntity.setAddresses(getAddressesEntity());
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
    final void testCreateUser_CreateUserServiceException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserDto userDto = new UserDto();
        userDto.setAddresses(getAddressesDto());
        userDto.setFirstName("Sergey");
        userDto.setLastName("Kasporov");
        userDto.setPassword("q3254213");
        userDto.setEmail("Kasporov@gmail.com");

        assertThrows(UserServiceException.class,
                () -> {
                    userService.createUser(userDto);
                });
    }

    @Test
    final void testCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateAddressId(anyInt())).thenReturn("htfghthm");
        when(utils.generateUserId(anyInt())).thenReturn(userID);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDto userDto = new UserDto();
        userDto.setAddresses(getAddressesDto());
        userDto.setFirstName("Sergey");
        userDto.setLastName("Kasporov");
        userDto.setPassword("q3254213");
        userDto.setEmail("Kasporov@gmail.com");

        UserDto storedUserDetails = userService.createUser(userDto);
        assertNotNull(storedUserDetails);
        assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
        assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
        assertNotNull(storedUserDetails.getUserId());
        assertEquals(storedUserDetails.getAddresses().size(), userEntity.getAddresses().size());
        verify(utils, times(storedUserDetails.getAddresses().size())).generateAddressId(30);
        verify(bCryptPasswordEncoder, times(1)).encode("q3254213");
        verify(userRepository, times(1)).save(any(UserEntity.class));

    }

    private List<AddressDTO> getAddressesDto() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setType("Shipping");
        addressDTO.setCity("Vancouver");
        addressDTO.setCountry("Canada");
        addressDTO.setPostalCode("ABCD1234");
        addressDTO.setStreetName("123 Street Name");

        AddressDTO billingAddressDTO = new AddressDTO();
        billingAddressDTO.setType("Shipping");
        billingAddressDTO.setCity("Vancouver");
        billingAddressDTO.setCountry("Canada");
        billingAddressDTO.setPostalCode("ABCD1234");
        billingAddressDTO.setStreetName("123 Street Name");

        List<AddressDTO> addresses = new ArrayList<>();
        addresses.add(addressDTO);
        addresses.add(billingAddressDTO);
        return addresses;
    }

    private List<AddressEntity> getAddressesEntity() {
        List<AddressDTO> addresses = getAddressesDto();

        Type listType = new TypeToken<List<AddressEntity>>() {
        }.getType();

        return new ModelMapper().map(addresses, listType);
    }


}