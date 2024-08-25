package com.hao;

import com.hao.controller.StuController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class GetBeanTest {

    @Autowired
    private ApplicationContext applicationContext; //注入IOC容器对象

    @Test
    public void testGetBean() {
        //根据bean的名称获取
        StuController bean1 = (StuController) applicationContext.getBean("stuController");
        System.out.println(bean1);

        //根据bean的类型获取
        StuController bean2 = applicationContext.getBean(StuController.class);
        System.out.println(bean2);

        //根据bean的名称及类型获取
        StuController bean3 = applicationContext.getBean("stuController", StuController.class);
        System.out.println(bean3);

    }

}
