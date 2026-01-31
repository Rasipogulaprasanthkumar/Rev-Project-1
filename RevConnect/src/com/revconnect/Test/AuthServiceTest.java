package com.revconnect.Test;
import com.revconnect.model.User;
import com.revconnect.service.AuthService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AuthServiceTest
{
    AuthService authService = new AuthService();

        @Test
        void testRegisterUser() {
            User user = new User();
            user.setUsername("test_junit");
            user.setEmail("test_junit@gmail.com");
            user.setPassword("12345");
            user.setRole("PERSONAL");

            boolean result = authService.Register(user);

            assertTrue(result);
        }

        @Test
        void testLoginSuccess() {
            User user = authService.login("test_junit@gmail.com", "12345");

            assertNotNull(user);
            assertEquals("test_junit", user.getUsername());
        }

        @Test
        void testLoginFail() {
            User user = authService.login("wrong@gmail.com", "0000");

            assertNull(user);
        }
    }

