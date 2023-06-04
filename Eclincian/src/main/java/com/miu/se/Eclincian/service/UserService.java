package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.User;

public interface UserService {
    public User createUser(User user);

    public User getUserById(Long id);

    public User updateUser(User user);

    public void deleteUser(Long id);
}
