package com.spring.service;

import com.spring.entity.UserEntity;
import com.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> getAll(){
        return userMapper.getAll();
    }

    public UserEntity getOne(Long id){
        return userMapper.getOne(id);
    }

    public void insert(UserEntity user){
        userMapper.insert(user);
    }

    public void update(UserEntity user){
        userMapper.update(user);
    }

    public void delete(Long id){
        userMapper.delete(id);
    }
}
