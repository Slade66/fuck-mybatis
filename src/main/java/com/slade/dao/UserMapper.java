package com.slade.dao;

import com.slade.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> findAllUsers();
}
