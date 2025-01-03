package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.auth.exception.InvalidPassword;
import com.kleintwins.ftr.auth.model.PasswordModel;
import com.kleintwins.ftr.auth.model.UserModel;
import com.kleintwins.ftr.auth.repository.PasswordRepository;
import com.kleintwins.ftr.core.service.ConfigService;
import com.kleintwins.ftr.core.service.I18nService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PasswordServiceTest {

    @Mock
    private PasswordRepository passwordRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ConfigService configService;

    @Mock
    private I18nService i18nService;

    @InjectMocks
    private PasswordService passwordService;

    private String userId;
    private String password;
    private UserModel userModel;
    private PasswordModel passwordModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = "12345";
        password = "Password123!";
        userModel = new UserModel("John", "Doe", "john.doe@example.com");
        passwordModel = PasswordModel.of(password, userModel);  // assuming PasswordModel constructor
    }

    @Test
    void shouldCreateNewPasswordAndMarkPreviousPasswordAsInactive() {
        // Arrange: Mock the behavior of passwordRepo
        when(passwordRepo.findByUserModelUserIdAndActive(userId, true)).thenReturn(Optional.of(passwordModel));
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        // Act: Call createPasswordForUser method
        passwordService.createPasswordForUser(password, userModel);

        // Assert: Verify that passwordRepo.save() was called twice (once for inactive password, once for the new password)
        verify(passwordRepo).findByUserModelUserIdAndActive(userId, true);
        verify(passwordRepo).save(passwordModel);  // Marking the previous password as inactive
        verify(passwordRepo).save(any(PasswordModel.class));  // Saving the new password
    }

    @Test
    void shouldCreateNewPasswordWhenNoPreviousPasswordExists() {
        // Arrange: Mock the behavior of passwordRepo to return empty for previous password
        when(passwordRepo.findByUserModelUserIdAndActive(userId, true)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        // Act: Call createPasswordForUser method
        passwordService.createPasswordForUser(password, userModel);

        // Assert: Verify that passwordRepo.save() was called once for the new password
        verify(passwordRepo).findByUserModelUserIdAndActive(userId, true);
        verify(passwordRepo).save(any(PasswordModel.class));  // Saving the new password
    }

    @Test
    void shouldThrowInvalidPasswordIfPasswordIsTooShort() {
        // Arrange: Set a minimum password length of 8 characters (via mocked configService)
        when(configService.getInt("api.auth.register.password.validation.minLength", 8)).thenReturn(8);
        when(configService.getInt("api.auth.register.password.validation.maxLength", 150)).thenReturn(150);

        // Act & Assert: Expect InvalidPassword exception to be thrown
        InvalidPassword exception = assertThrows(InvalidPassword.class, () -> {
            passwordService.validatePassword("short");
        });

        assertEquals("Password is too short.", exception.getMessage());  // assuming i18nService translation key is correctly set
    }

    @Test
    void shouldThrowInvalidPasswordIfPasswordIsTooLong() {
        // Arrange: Set a maximum password length of 150 characters (via mocked configService)
        when(configService.getInt("api.auth.register.password.validation.minLength", 8)).thenReturn(8);
        when(configService.getInt("api.auth.register.password.validation.maxLength", 150)).thenReturn(150);

        // Act & Assert: Expect InvalidPassword exception to be thrown
        InvalidPassword exception = assertThrows(InvalidPassword.class, () -> {
            passwordService.validatePassword("thispasswordiswaytoolongandshouldfailvalidationbecauseitisover150characters......");
        });

        assertEquals("Password is too long.", exception.getMessage());  // assuming i18nService translation key is correctly set
    }

    @Test
    void shouldThrowInvalidPasswordIfPasswordIsMissing() {
        // Arrange: Set a minimum password length of 8 characters (via mocked configService)
        when(configService.getInt("api.auth.register.password.validation.minLength", 8)).thenReturn(8);
        when(configService.getInt("api.auth.register.password.validation.maxLength", 150)).thenReturn(150);

        // Act & Assert: Expect InvalidPassword exception to be thrown
        InvalidPassword exception = assertThrows(InvalidPassword.class, () -> {
            passwordService.validatePassword("");  // Empty password
        });

        assertEquals("Password is missing.", exception.getMessage());  // assuming i18nService translation key is correctly set
    }

    @Test
    void shouldReturnActivePasswordForUser() {
        // Arrange: Mock the behavior of passwordRepo to return an active password for the user
        when(passwordRepo.findByUserModelUserIdAndActive(userId, true)).thenReturn(Optional.of(passwordModel));

        // Act: Call getActivePasswordForUser method
        Optional<PasswordModel> result = passwordService.getActivePasswordforUser(userId);

        // Assert: Verify that the result is the expected active password
        assertTrue(result.isPresent());
        assertEquals(passwordModel, result.get());
    }

    @Test
    void shouldReturnEmptyIfNoActivePasswordExistsForUser() {
        // Arrange: Mock the behavior of passwordRepo to return empty (no active password)
        when(passwordRepo.findByUserModelUserIdAndActive(userId, true)).thenReturn(Optional.empty());

        // Act: Call getActivePasswordForUser method
        Optional<PasswordModel> result = passwordService.getActivePasswordforUser(userId);

        // Assert: Verify that the result is empty
        assertFalse(result.isPresent());
    }
}