package com.spring.service;

import com.spring.entity.User;
import com.spring.mapper.UserMapper;
import com.spring.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

    @Transactional
    public void insert(User user,MultipartFile file){

        String fileName = FileUtils.fileUpload(file);
        user.setFilePath(fileName);
        userMapper.insert(user);
    }

    @Transactional
    public void update(User user,MultipartFile file){

        String fileName = FileUtils.fileUpload(file);
        user.setFilePath(fileName);
        userMapper.update(user);
    }

    public void delete(Long id){
        userMapper.delete(id);
    }
}
