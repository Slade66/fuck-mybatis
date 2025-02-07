package com.slade.test;

import com.slade.pojo.User;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 频繁创建和关闭数据库的连接会影响性能 -> 使用连接池
 * SQL语句是硬编码的，难以拓展 -> 配置文件
 * SQL语句的变化也会连带解析结果集的代码跟着变化 -> 反射
 * */

public class TestJDBC {

    @Test
    public void testSelect() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<User>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载类的时候会执行静态代码块，在那里面有注册驱动的代码
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuck-java", "root", "mlyzga78");
            preparedStatement = connection.prepareStatement("SELECT * FROM user");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                users.add(new User(id, username));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(users); // ArrayList 的 `toString() 方法会依次调用列表中每个元素的 toString() 方法，并将它们用逗号分隔。
    }

    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<User>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载类的时候会执行静态代码块，在那里面有注册驱动的代码
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuck-java", "root", "mlyzga78");
            preparedStatement = connection.prepareStatement("INSERT INTO user VALUES(null, ?)");
            preparedStatement.setObject(1, "小泽");
            int count = preparedStatement.executeUpdate();
            System.out.println(count > 0 ? "插入成功" : "插入失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
