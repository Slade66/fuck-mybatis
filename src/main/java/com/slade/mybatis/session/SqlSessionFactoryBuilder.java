package com.slade.mybatis.session;

import com.slade.mybatis.config.Configuration;
import com.slade.mybatis.config.XMLConfigBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        Configuration configuration = new Configuration();
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(configuration);
        xmlConfigBuilder.parse(inputStream);
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }
}
