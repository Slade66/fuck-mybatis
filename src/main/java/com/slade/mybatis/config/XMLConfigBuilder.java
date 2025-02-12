package com.slade.mybatis.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.slade.mybatis.io.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {
    Configuration configuration;

    public XMLConfigBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration parse(InputStream inputStream) throws DocumentException, PropertyVetoException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elements = root.selectNodes("property");
        Properties properties = new Properties();
        for (Element element : elements) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("driver"));
        dataSource.setJdbcUrl(properties.getProperty("url"));
        dataSource.setUser(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(dataSource);

        List<Element> mappers = root.selectNodes("mapper");
        for (Element mapper : mappers) {
            String resource = mapper.attributeValue("resource");
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            InputStream mapperInputStream = Resources.getResourceAsStream(resource);
            xmlMapperBuilder.parse(mapperInputStream);
        }

        return configuration;
    }
}
