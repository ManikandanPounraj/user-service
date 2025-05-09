package com.rak.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rak.user.model.User;
import com.rak.user.repository.UserRepository;
import com.rak.user.service.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testCreateUser() {
        User input = new User(2L, "Alice", "alice@example.com");
        User saved = new User(1L, "Alice", "alice@example.com");

        when(userRepository.save(input)).thenReturn(saved);

        User result = userService.createUser(input);
        assertEquals("Alice", result.getName());
        assertEquals(1L, result.getUserId());
    }

    @Test
    void testGetUserById_Found() {
        User user = new User(1L, "Bob", "bob@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUser(1L);
        assertEquals("Bob", result.getName());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        //assertThrows(NoSuchElementException.class, () -> userService.getUser(99L));
    }

    @Test
    void testUpdateUser() {
        User existing = new User(1L, "Alice", "alice@example.com");
        User updated = new User(1L, "Alice Updated", "alice.updated@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any())).thenReturn(updated);

        User result = userService.updateUser(1L, updated);
        assertEquals("Alice Updated", result.getName());
        assertEquals("alice.updated@example.com", result.getEmail());
    }

//    @Test
//    void testDeleteUser() {
//        doNothing().when(userRepository).deleteById(1L);
//        userService.deleteUser(1L);
//        verify(userRepository, times(1)).deleteById(1L);
//    }
}