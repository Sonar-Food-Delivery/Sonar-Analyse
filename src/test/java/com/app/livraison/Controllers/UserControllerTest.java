package com.app.livraison.Controllers;

import com.app.livraison.controller.UserController;
import com.app.livraison.entities.User;
import com.app.livraison.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("JohnDoe");
    }

    @Test
    void testSignUp() {
        when(userService.signUp(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = userController.signUp(user);
        User createdUser = (User) response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(createdUser);
        assertEquals("JohnDoe", createdUser.getUsername());
        verify(userService, times(1)).signUp(any(User.class));
    }
}
