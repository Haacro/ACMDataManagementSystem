package com.hao;

import com.google.gson.Gson;
import com.hao.pojo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 自动配置原理测试
 */
@SpringBootTest
public class AutoConfigurationTest {

    @Autowired
    private Gson gson;

    @Test
    public void testJson() {
        String json = gson.toJson(Result.success());
        System.out.println(json);
    }
}
