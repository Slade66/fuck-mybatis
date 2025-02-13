package com.slade.test;

import com.slade.dao.UserMapper;
import com.slade.mybatis.io.Resources;
import com.slade.mybatis.session.SqlSession;
import com.slade.mybatis.session.SqlSessionFactory;
import com.slade.mybatis.session.SqlSessionFactoryBuilder;
import com.slade.pojo.User;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis {

    UserMapper userMapper;

    @Before
    public void init() throws PropertyVetoException, DocumentException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        userMapper = session.getMapper(UserMapper.class);
    }

    @Test
    public void test() {
        List<User> allUsers = userMapper.findAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

}
