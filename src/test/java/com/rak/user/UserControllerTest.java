package com.rak.user;

import com.rak.user.controller.UserController;
import com.rak.user.model.User;
import com.rak.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void testCreateUser() {
        User input = new User(2L, "Alice", "alice@example.com");
        User created = new User(1L, "Alice", "alice@example.com");

        when(userService.createUser(input)).thenReturn(created);

        ResponseEntity<User> response = userController.create(input);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Alice", response.getBody().getName());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(
                new User(1L, "A", "a@mail.com"),
                new User(2L, "B", "b@mail.com")
        );
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAll();
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetUserById_Found() {
        User user = new User(1L, "C", "c@mail.com");
        when(userService.getUser(1L)).thenReturn(user);

        ResponseEntity<User> response = userController.get(1L);
        assertEquals("C", response.getBody().getName());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userService.getUser(10L)).thenThrow(new NoSuchElementException("User not found"));
        assertThrows(NoSuchElementException.class, () -> userController.get(10L));
    }

    @Test
    void testUpdateUser() {
        User updated = new User(1L, "Updated", "updated@mail.com");
        when(userService.updateUser(eq(1L), any())).thenReturn(updated);

        ResponseEntity<User> response = userController.update(1L, updated);
        assertEquals("Updated", response.getBody().getName());
    }

//    @Test
//    void testDeleteUser() {
//    	User created = new User(1L, "Alice", "alice@example.com");
//    	userService.createUser(created);
//        doNothing().when(userService).deleteUser(1L);
//        ResponseEntity<Void> response = userController.delete(1L);
//        assertEquals(204, response.getStatusCodeValue());
//        verify(userService, times(1)).deleteUser(1L);
//    }
//
//    @Test
//    void testValidateUser() {
//        doNothing().when(userService).validateUser(1L);
//        ResponseEntity<Void> response = userController.validate(1L);
//        assertEquals(200, response.getStatusCodeValue());
//        verify(userService, times(1)).validateUser(1L);
//    }
}
