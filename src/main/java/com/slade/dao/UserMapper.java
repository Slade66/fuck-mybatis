package com.slade.dao;

import com.slade.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> findAllUsers();

    User findUserById(int id);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUserById(User user);
}
