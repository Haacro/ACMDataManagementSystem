package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import com.hao.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"http://localhost:5175", "http://localhost:7000"}) //允许跨域请求
@RestController
public class LoginController {

    @Autowired
    private StuService stuService;

    @PostMapping("/login")
    public Result login(@RequestBody Stu stu) {
        log.info("学生用户登录:{}", stu);
        Stu s = stuService.login(stu);

        //登陆成功，生成令牌，下发令牌
        if (s != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", s.getId());
            claims.put("stuNo", s.getStuNo());
            claims.put("stuName", s.getStuName());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的学生用户信息
            return Result.success(jwt);
        }

        //登陆失败，返回错误信息
        return Result.error("用户名或密码错误！");
    }
}
