package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User save(User user) {
        return repo.save(user);
    }

    public Optional<User> get(Long id) {
        return repo.findById(id);
    }

    public Optional<User> update(Long id, User updated) {
        return repo.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            existing.setPhone(updated.getPhone());
            return repo.save(existing);
        });
    }
}
