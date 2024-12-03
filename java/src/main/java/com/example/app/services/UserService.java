package com.example.app.services;

import com.example.app.dtos.UserDTO;
import com.example.app.entities.User;
import com.example.app.entities.RoleEnum;
import com.example.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Vérifier si un email existe déjà dans la base de données
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Création d'un nouvel utilisateur
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setNom(userDTO.getNom());
        user.setEmail(userDTO.getEmail());
        user.setMotDePasse(passwordEncoder.encode("defaultPassword")); // Mot de passe par défaut
        user.setRole(RoleEnum.valueOf(userDTO.getRole()));

        userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    // Trouver un utilisateur par ID
    public UserDTO findById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return new UserDTO(user.getId(), user.getNom(), user.getEmail(), user.getRole().name());
        }
        return null;
    }

    // Mise à jour d'un utilisateur
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setNom(userDTO.getNom());
            user.setEmail(userDTO.getEmail());
            user.setRole(RoleEnum.valueOf(userDTO.getRole()));
            userRepository.save(user);
            return userDTO;
        }
        return null;
    }

    // Suppression d'un utilisateur
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Authentification d'un utilisateur
    public User authenticate(String email, String motDePasse) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(motDePasse, user.getMotDePasse())) {
                return user;
            }
        }
        return null;
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
