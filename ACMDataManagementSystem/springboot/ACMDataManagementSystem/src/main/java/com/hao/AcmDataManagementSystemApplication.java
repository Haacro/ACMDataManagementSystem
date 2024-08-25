package com.hao;

import org.dom4j.io.SAXReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan //开启了对servlet组件的支持
@SpringBootApplication
public class AcmDataManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmDataManagementSystemApplication.class, args);
    }

    /*//声明第三方Bean
    @Bean //将当前方法的返回值对象交给IOC容器管理，成为IOC容器Bean
    public SAXReader saxReader() {
        return new SAXReader();
    }*/
}
