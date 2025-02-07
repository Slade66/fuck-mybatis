package com.slade.test;

import com.slade.dao.UserMapper;
import com.slade.pojo.User;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {

    UserMapper userMapper;

    @Before
    public void init() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }

    @Test
    public void test() {
        List<User> allUsers = userMapper.findAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

}
