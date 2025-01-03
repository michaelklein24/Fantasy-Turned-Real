package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.auth.exception.AccountAlreadyExists;
import com.kleintwins.ftr.auth.model.UserModel;
import com.kleintwins.ftr.auth.repository.UserRepository;
import com.kleintwins.ftr.core.service.I18nService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordService passwordService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private I18nService i18nService;

    @InjectMocks
    private AuthService authService;

    private String existingEmail;
    private String newEmail;
    private String firstName;
    private String lastName;
    private String password;

    @BeforeEach
    void setUp() {
        // Initialize test data
        existingEmail = "existing.email@example.com";
        newEmail = "new.email@example.com";
        firstName = "John";
        lastName = "Doe";
        password = "Password123!";

        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowAccountAlreadyExistsExceptionIfEmailUsedForRegistrationWasAlreadyUsedForAnotherAccount() {
        // Arrange: Mock the repository to simulate email already in use
        when(userRepo.existsByEmail(existingEmail)).thenReturn(true);
        when(i18nService.translate(eq("api.auth.register.response.error.emailAlreadyExists.message"), eq(existingEmail)))
                .thenReturn("Account with email 'existing.email@example.com' already exists.");

        // Act & Assert: Expect an AccountAlreadyExistsException to be thrown
        AccountAlreadyExists exception = assertThrows(AccountAlreadyExists.class, () -> {
            authService.registerUser(firstName, lastName, existingEmail, password);
        });

        // Assert: Verify that the exception message is correct
        assertEquals("Account with email 'existing.email@example.com' already exists.", exception.getMessage());

        // Verify that the repository method was called and the translation was done
        verify(userRepo).existsByEmail(existingEmail);
        verify(i18nService).translate(eq("api.auth.register.response.error.emailAlreadyExists.message"), eq(existingEmail));
    }

    @Test
    void shouldSuccessfullyRegisterUserIfEmailIsNotAlreadyUsed() {
        // Arrange: Mock the repository to simulate email is not in use
        when(userRepo.existsByEmail(newEmail)).thenReturn(false);
        when(i18nService.translate(anyString(), anyString())).thenReturn("mocked message"); // Mock translation

        // Mock other services
        doNothing().when(passwordService).validatePassword(anyString());
        doNothing().when(passwordService).createPasswordForUser(anyString(), any(UserModel.class));
        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));

        // Act: Call the register method
        UserModel result = authService.registerUser(firstName, lastName, newEmail, password);

        // Assert: Verify the result is as expected (user is saved, exception isn't thrown)
        assertNotNull(result);
        assertEquals(newEmail, result.getEmail());

        // Verify that userRepo.save() was called with the correct parameters
        verify(userRepo).save(any(UserModel.class));
        verify(passwordService).validatePassword(password);
        verify(passwordService).createPasswordForUser(password, result);
        verify(authenticationManager).authenticate(any());
    }
}