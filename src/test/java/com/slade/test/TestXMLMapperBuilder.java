package com.slade.test;

import com.slade.mybatis.config.Configuration;
import com.slade.mybatis.config.XMLMapperBuilder;
import com.slade.mybatis.io.Resources;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.InputStream;

public class TestXMLMapperBuilder {
    @Test
    public void test() throws DocumentException {
        Configuration cfg = new Configuration();
        InputStream inputStream = Resources.getResourceAsStream("mappers/UserMapper.xml");
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(cfg);
        xmlMapperBuilder.parse(inputStream);
    }
}
