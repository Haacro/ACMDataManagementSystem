package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private StuService stuService;

    @PostMapping("/login")
    public Result login(@RequestBody Stu stu) {
        log.info("学生用户登录:{}", stu);
        Stu s = stuService.login(stu);
        return s != null?Result.success():Result.error("用户名或密码错误！");
    }
}
