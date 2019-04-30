package com.nowcoder.mvcdemo.mapper;

import com.nowcoder.mvcdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectByUsername(String username);

    List<User> selectAll();

    User selectById(int id);

    void delete(int id);

    void insert(User user);

    void update(User user);

}
