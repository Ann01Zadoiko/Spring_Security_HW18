package com.example.please.service;

import com.example.please.entity.User;
import com.example.please.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IService<User> {

    private final UserRepository repository;

    @Override
    public List<User> listAll() {
        return repository.findAll();
    }

    @Override
    public User add(User entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(User entity) {
        repository.save(entity);
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }


}
