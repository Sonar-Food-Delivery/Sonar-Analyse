package com.app.livraison.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.app.livraison.entities.User;
import com.app.livraison.service.JwtService;
import com.app.livraison.service.UserService;

import java.util.List;
import java.util.Map;

@Configuration
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            User savedUser = userService.signUp(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);  // Appeler la méthode findById du service
            return ResponseEntity.ok(user);  // Retourner l'utilisateur trouvé avec un code HTTP 200
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);  // En cas d'erreur, retourner l'erreur correspondante
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        try {
            User authenticatedUser = userService.login(user.getEmail(), user.getPassword());
            String jwtToken = jwtService.generateToken(authenticatedUser);

            return ResponseEntity.ok(Map.of(
                    "token", jwtToken,
                    "userId", String.valueOf(authenticatedUser.getId()),
                    "username", authenticatedUser.getUsername(),
                    "userRole", authenticatedUser.getUserRole()
            ));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", e.getReason()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProfile(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateProfile(id, user);

        // Generate a new token after the update
        String newToken = jwtService.generateToken(updatedUser);

        return ResponseEntity.ok(Map.of(
                "user", updatedUser,
                "token", newToken
        ));
    }



}
