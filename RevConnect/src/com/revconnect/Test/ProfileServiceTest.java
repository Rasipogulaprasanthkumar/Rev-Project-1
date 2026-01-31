package com.revconnect.Test;
import com.revconnect.model.Profile;
import com.revconnect.service.ProfileService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProfileServiceTest
{
    ProfileService profileService = new ProfileService();

        @Test
        void testCreateProfile() {
            Profile p = new Profile();
            p.setUserId(1);
            p.setName("JUnit User");
            p.setBio("Testing bio");
            p.setLocation("India");
            p.setWebsite("junit.com");
            p.setCategory("CREATOR");

            boolean result = profileService.createProfile(p);

            assertTrue(result);
        }

        @Test
        void testViewProfile() {
            Profile p = profileService.viewProfile(1);

            assertNotNull(p);
            assertEquals("JUnit User", p.getName());
        }
    }

