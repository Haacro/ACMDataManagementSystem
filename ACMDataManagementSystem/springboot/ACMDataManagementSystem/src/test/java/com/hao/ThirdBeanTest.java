package com.hao;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThirdBeanTest {

    @Autowired
    private SAXReader saxReader;

    @Test
    public void testThirdBean() throws DocumentException {
        //SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(this.getClass().getClassLoader().getResource("test.xml"));
        Element rootElement = document.getRootElement();
        String stu_name = rootElement.element("stu_name").getText();
        String class_name = rootElement.element("class_name").getText();

        System.out.println(stu_name + " : " + class_name);
    }

}
