package com.alex.web.storage.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.alex.web.domain.User;
import com.alex.web.storage.UserCrudDao;
import com.google.inject.Singleton;

@Singleton
public class UserCrudDaoImpl implements UserCrudDao {
    private final Map<String, User> userRepository = new ConcurrentHashMap<>();

    {
        User user = new User(generateUUID(), "Alex", "s3cret");
        userRepository.put(user.getId(), user);
        user = new User(generateUUID(), "Jack", "s3cret$$");
        userRepository.put(user.getId(), user);
        user = new User(generateUUID(), "John", "1234567");
        userRepository.put(user.getId(), user);
        user = new User(generateUUID(), "Michael", "555oops");
        userRepository.put(user.getId(), user);
    }

    @Override
    public String create(User entity) {
        String id = UUID.randomUUID().toString();
        entity.setId(id);
        userRepository.put(id, entity);
        return id;
    }

    @Override
    public User read(String identity) {
        return userRepository.get(identity);
    }

    @Override
    public void update(User entity) {
        userRepository.replace(entity.getId(), entity);
    }

    @Override
    public void delete(String identity) {
        userRepository.remove(identity);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository.values());
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
