package com.example.mobileappws;

import com.example.mobileappws.shared.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilsTest {

    @Autowired
    Utils utils;

    @BeforeEach
    void setUp() {
    }

    @Test
    final void testGenerateUserId() {
//        fail("NOT YET IMPLEMENTED");
        String userId = utils.generateUserId(30);
        String userId2 = utils.generateUserId(30);

        assertNotNull(userId);
        assertNotNull(userId2);

        assertTrue(userId.length() == 303);
        assertTrue(!userId.equalsIgnoreCase(userId2));
    }

    @Test
//    @Disabled
    final void testHasTokenNotExpired() {
//        fail("NOT YET IMPLEMENTED");
        String token = utils.generateEmailVerificationToken("fekfket54424lkmMDSFerw3");
        assertNotNull(token);

        boolean hasTokenExpired = Utils.hasTokenExpired(token);
        assertFalse(hasTokenExpired);
    }

    @Test
    final void testHasTokeExpired() {
        String expiredToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlcmZhbmFraGF2YW5yYWRAbmUxMjJkMjIzZDJvLmNvbSIsImV4cCI6MTY3MDU3ODgxOX0.Rf4BgwRMKEPPG5ltR03Hv4myNUY7B4yE09zSwG7JYSBihhJqFLHfMgpO1UGdgazOk6oHXKoQ2Dd8yttWBaFeIQ";
        boolean hasTokenExpired = Utils.hasTokenExpired(expiredToken);

        assertTrue(hasTokenExpired);
    }


}
