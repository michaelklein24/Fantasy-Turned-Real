package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.user.exception.InvalidPassword;
import com.kleintwins.ftr.core.service.ConfigService;
import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.user.model.PasswordModel;
import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.repository.PasswordRepository;
import com.kleintwins.ftr.user.service.PasswordService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PasswordServiceTest {

    @Mock
    private PasswordRepository passwordRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private I18nService i18nService;

    @Mock
    private ConfigService configService;

    @InjectMocks
    private PasswordService passwordService;

    private UserModel userModel;
    private String decodedExistingPassword = "passwordisfake";
    private PasswordModel existingPasswordModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userModel = new UserModel("tony", "stark", "testuser@example.com");
        userModel.setUserId(UUID.randomUUID().toString());
        existingPasswordModel = PasswordModel.of(decodedExistingPassword, userModel);
        existingPasswordModel.setPasswordId(UUID.randomUUID().toString());
    }

    @Test
    void shouldCreateNewPasswordAndMarkPreviousPasswordAsInactive() {
        // Arrange: We mock that there is an existing active password
        String newPassword = "newPassword123";
        when(passwordRepo.findByUserModelUserIdAndActive(userModel.getUserId(), true)).thenReturn(Optional.of(existingPasswordModel));
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword123");

        // Act: Call createPasswordForUser method
        passwordService.createPasswordForUser(newPassword, userModel);

        // Log to ensure the method is being hit with correct parameters
        System.out.println("User ID in test: " + userModel.getUserId());

        // Assert: Verify that passwordRepo.save() was called twice (once for inactive password, once for the new password)
        verify(passwordRepo, times(1)).findByUserModelUserIdAndActive(anyString(), anyBoolean()); // verify that the mock was called with `any()` userId
        verify(passwordRepo, times(2)).save(any(PasswordModel.class));  // verify that the previous password was saved as inactive
    }

    @Test
    void shouldCreateNewPasswordWhenNoPreviousPasswordExists() {
        // Arrange: We mock that there is no existing active password
        String newPassword = "newPassword123";
        when(passwordRepo.findByUserModelUserIdAndActive(userModel.getUserId(), true)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword123");

        // Act: Create a new password for the user
        passwordService.createPasswordForUser(newPassword, userModel);

        // Assert: Verify that a new password was created and saved
        verify(passwordRepo).save(any(PasswordModel.class)); // Ensure save is called for the new password
    }

    @Test
    void shouldThrowInvalidPasswordIfPasswordIsTooShort() {
        // Arrange: We set a password that's shorter than the minimum length
        String shortPassword = "short";
        when(configService.getInt("api.auth.register.password.validation.minLength", 8)).thenReturn(8);
        when(configService.getInt("api.auth.register.password.validation.maxLength", 150)).thenReturn(150);
        when(i18nService.translate(anyString())).thenReturn("passwordTooShort");

        // Act & Assert: We expect InvalidPassword exception for too short password
        InvalidPassword thrown = assertThrows(InvalidPassword.class, () -> passwordService.validatePassword(shortPassword));
        assertTrue(thrown.getMessage().contains("passwordTooShort")); // The error message should contain "passwordTooShort"
    }

    @Test
    void shouldThrowInvalidPasswordIfPasswordIsTooLong() {
        // Arrange: We set a password that's longer than the maximum length
        String longPassword = StringUtils.repeat("a", 200); // 200 chars long
        when(configService.getInt("api.auth.register.password.validation.minLength", 8)).thenReturn(8);
        when(configService.getInt("api.auth.register.password.validation.maxLength", 150)).thenReturn(150);
        when(i18nService.translate(anyString())).thenReturn("passwordTooLong");

        // Act & Assert: We expect InvalidPassword exception for too long password
        InvalidPassword thrown = assertThrows(InvalidPassword.class, () -> passwordService.validatePassword(longPassword));
        assertTrue(thrown.getMessage().contains("passwordTooLong")); // The error message should contain "passwordTooLong"
    }

    @Test
    void shouldThrowInvalidPasswordIfPasswordIsMissing() {
        // Arrange: We set an empty password (missing password)
        String missingPassword = "";
        when(configService.getInt("api.auth.register.password.validation.minLength", 8)).thenReturn(8);
        when(configService.getInt("api.auth.register.password.validation.maxLength", 150)).thenReturn(150);
        when(i18nService.translate(anyString())).thenReturn("passwordMissing");

        // Act & Assert: We expect InvalidPassword exception for missing password
        InvalidPassword thrown = assertThrows(InvalidPassword.class, () -> passwordService.validatePassword(missingPassword));
        assertTrue(thrown.getMessage().contains("passwordMissing")); // The error message should contain "missingPassword"
    }

    @Test
    void shouldReturnActivePasswordForUser() {
        // Arrange: We mock that there is an active password for the user
        when(passwordRepo.findByUserModelUserIdAndActive(userModel.getUserId(), true)).thenReturn(Optional.of(existingPasswordModel));

        // Act: Fetch the active password for the user
        Optional<PasswordModel> result = passwordService.getActivePasswordforUser(userModel.getUserId());

        // Assert: Verify the result contains the active password
        assertTrue(result.isPresent()); // The result should be present
        assertEquals(existingPasswordModel, result.get()); // The result should match the existing password
    }

    @Test
    void shouldReturnEmptyIfNoActivePasswordExistsForUser() {
        // Arrange: We mock that there is no active password for the user
        when(passwordRepo.findByUserModelUserIdAndActive(userModel.getUserId(), true)).thenReturn(Optional.empty());

        // Act: Fetch the active password for the user
        Optional<PasswordModel> result = passwordService.getActivePasswordforUser(userModel.getUserId());

        // Assert: Verify the result is empty (no active password found)
        assertFalse(result.isPresent()); // The result should be empty
    }
}