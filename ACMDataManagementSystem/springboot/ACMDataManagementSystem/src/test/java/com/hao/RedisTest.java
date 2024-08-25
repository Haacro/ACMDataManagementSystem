package com.hao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.pojo.SubNum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest //在测试类添加该注解后单元测试方法执行前先初始化Spring容器
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet() {
        //向redis中存储一个键值对
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("name", "hao");
        stringStringValueOperations.set("age", "21", 15, TimeUnit.SECONDS);
    }

    @Test
    public void testGet() {
        //向从redis中获取一个键值对
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println(stringStringValueOperations.get("name"));
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSaveStu() throws JsonProcessingException {
        // 创建对象
        SubNum subNum = new SubNum("hao", 21);
        // 手动序列化
        String json = mapper.writeValueAsString(subNum);
        // 写入数据
        stringRedisTemplate.opsForValue().set("user:100", json);
        // 获取数据
        String jsonSubNum = stringRedisTemplate.opsForValue().get("user:100");
        // 手动反序列化
        SubNum subNum1 = mapper.readValue(jsonSubNum, SubNum.class);
        System.out.println("subNum1 = " + subNum1);
    }

    @Test
    public void testHash() {
        stringRedisTemplate.opsForHash().put("user:200", "name", "hao");
        stringRedisTemplate.opsForHash().put("user:200", "age", "21");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:200");
        System.out.println("entries = " + entries);
    }
}
