package com.example.mobileappws;

import com.example.mobileappws.io.entity.UserEntity;
import com.example.mobileappws.io.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception {
//        // Prepare User Entity
//        UserEntity userEntity = new UserEntity();
//        userEntity.setFirstName("Ellie");
//        userEntity.setLastName("Whatever");
//        userEntity.setUserId("534rjmoelfm34l5e54w;r");
//        userEntity.setEncryptedPassword("sfgy,tl;jut6y6rye5;r");
//        userEntity.setEmailVerificationToken("Ellie@lastofus.com");
//        userEntity.setEmailVerificationStatus(false);
//
//        // Prepare User Address
//        AddressEntity addressEntity = new AddressEntity();
//        addressEntity.setType("shipping");
//        addressEntity.setAddressID("f453wrwe");
//        addressEntity.setCity("California");
//        addressEntity.setCountry("America");
//        addressEntity.setPostalCode("3221");
//        addressEntity.setStreetName("Elm");
//
//        List<AddressEntity> addresses = new ArrayList<>();
//        addresses.add(addressEntity);
//
//        userEntity.setAddresses(addresses);
//
//        userRepository.save(userEntity);
//
//
//        // Prepare Second User Entity
//        UserEntity userEntity2 = new UserEntity();
//        userEntity2.setFirstName("Ellie");
//        userEntity2.setLastName("Whatever");
//        userEntity2.setUserId("534rjmoelfm34l52424e54w;r");
//        userEntity2.setEncryptedPassword("sfgy,tl;jut6y6rye5;r");
//        userEntity2.setEmailVerificationToken("Ellie@lastofus.com");
//        userEntity2.setEmailVerificationStatus(false);
//
//        // Prepare Second User Address
//        AddressEntity addressEntity2 = new AddressEntity();
//        addressEntity2.setType("shipping");
//        addressEntity2.setAddressID("f45242343wrwe");
//        addressEntity2.setCity("California");
//        addressEntity2.setCountry("America");
//        addressEntity2.setPostalCode("3221");
//        addressEntity2.setStreetName("Elm");
//
//        List<AddressEntity> addresses2 = new ArrayList<>();
//        addresses2.add(addressEntity2);
//
//        userEntity.setAddresses(addresses2);
//
//        userRepository.save(userEntity2);
//

    }

    @Test
    final void testGetVerifiedUsers() {
        Pageable pageableRequest = PageRequest.of(0, 1);
        Page<UserEntity> pages = userRepository.findAllUsersWithConfirmedEmailAddress(pageableRequest);
        assertNotNull(pages);

        List<UserEntity> userEntities = pages.getContent();
        assertNotNull(userEntities);
        assertTrue(userEntities.size() == 1);
    }

}
