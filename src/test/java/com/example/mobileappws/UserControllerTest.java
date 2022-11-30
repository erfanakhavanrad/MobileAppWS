package com.example.mobileappws;

import com.example.mobileappws.service.impl.UserServiceImpl;
import com.example.mobileappws.shared.dto.AddressDTO;
import com.example.mobileappws.shared.dto.UserDto;
import com.example.mobileappws.ui.controller.UserController;
import com.example.mobileappws.ui.model.response.UserRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    UserDto userDto;

    final String USER_ID = "pQbJEg7Clh44n4RZpJ4gBPenaa4sJf";

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userDto = new UserDto();
        userDto.setFirstName("Sergey");
        userDto.setLastName("Kargopolov");
        userDto.setEmail("test@test.com");
        userDto.setEmailVerificationStatus(Boolean.FALSE);
        userDto.setEmailVerificationToken(null);
        userDto.setUserId(USER_ID);
        userDto.setAddresses(getAddressesDto());
        userDto.setEncryptedPassword("xcf58tugh47");

    }

    @Test
    final void testGetUser() {
//        fail("Not yet implemented.");
        System.out.println("Before");
        when(userService.getUserByUserId(anyString())).thenReturn(userDto);
        System.out.println("After");
        UserRest userRest = userController.getUserByID(USER_ID);

        assertNotNull(userRest);
        assertEquals(USER_ID, userRest.getUserID());
        assertEquals(userDto.getFirstName(), userRest.getFirstName());
        assertEquals(userDto.getLastName(), userRest.getLastName());
        assertTrue(userDto.getAddresses().size() == userRest.getAddresses().size());

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

}
