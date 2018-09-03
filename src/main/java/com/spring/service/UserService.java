package com.spring.service;

import com.spring.entity.User;
import com.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.getAll();
    }

    public User getOne(Long id){
        return userMapper.getOne(id);
    }

    public void insert(User user){
        userMapper.insert(user);
    }

    public void update(User user){
        userMapper.update(user);
    }

    public void delete(Long id){
        userMapper.delete(id);
    }
}
