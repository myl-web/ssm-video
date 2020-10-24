package com.wm.video.service;

import com.wm.video.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findUser(User user);

    void addUser(User user);

    User findByEmail(String email);

    void updateUser(User user);
}
