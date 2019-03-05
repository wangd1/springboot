package com.spring.service;

import com.spring.mapper.BraveInfoMapper;
import com.spring.entity.BraveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BraveInfoService {

    @Autowired
    private BraveInfoMapper braveInfoMapper;

    public List<BraveInfo> getAll(){
        return braveInfoMapper.getAll();
    }

    public BraveInfo getOne(String id){

        return braveInfoMapper.getOne(id);
    }

    @Transactional
    public void insert(BraveInfo user,MultipartFile file){

        //String fileName = FileUtils.fileUpload(file);
        //user.setFilePath(fileName);
        braveInfoMapper.insert(user);
    }

    @Transactional
    public void update(BraveInfo user,MultipartFile file){

        //String fileName = FileUtils.fileUpload(file);
        //user.setFilePath(fileName);
        braveInfoMapper.update(user);
    }

    public void delete(String id){
        braveInfoMapper.delete(id);
    }
}
