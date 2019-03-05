package com.spring.mapper;

import com.spring.entity.BraveInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BraveInfoMapper {
    List<BraveInfo> getAll();

    BraveInfo getOne(String id);

    void insert(BraveInfo braveInfo);

    void update(BraveInfo braveInfo);

    void delete(String id);

}
