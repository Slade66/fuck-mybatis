package com.slade.test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class TestDom4J {
    @Test
    public void test() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        // Document document = saxReader.read("TestDom4J.xml");
        // 在 Maven 项目中，src/test/resources 下的文件默认会在测试运行时自动加载。如果代码中直接访问文件路径，而未使用类加载器读取，就会报找不到文件的错误。
        Document document = saxReader.read(getClass().getClassLoader().getResourceAsStream("TestDom4J.xml"));
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for (Element element : list) {
            Attribute attrId = element.attribute("id");
            String id = attrId.getValue();
            System.out.println(id);
            List<Element> children = element.elements();
            for (Element child : children) {
                String tagName = child.getName();
                String value = child.getText();
                System.out.println(tagName + ":" + value);
            }
            System.out.println("=".repeat(25));
        }
    }
}
