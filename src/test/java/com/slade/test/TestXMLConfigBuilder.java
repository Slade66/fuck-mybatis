package com.slade.test;

import com.slade.mybatis.config.Configuration;
import com.slade.mybatis.config.XMLConfigBuilder;
import com.slade.mybatis.io.Resources;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class TestXMLConfigBuilder {
    @Test
    public void test() throws PropertyVetoException, DocumentException {
        Configuration config = new Configuration();
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(config);
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        xmlConfigBuilder.parse(inputStream);
        System.out.println(config);
    }
}
