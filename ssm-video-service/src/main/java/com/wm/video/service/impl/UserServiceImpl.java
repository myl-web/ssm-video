package com.wm.video.service.impl;

import com.wm.video.dao.UserMapper;
import com.wm.video.pojo.User;
import com.wm.video.pojo.UserExample;
import com.wm.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findUser(User user) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(user.getEmail());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findByEmail(String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);
        User user = userList.get(0);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
