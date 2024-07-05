package com.hao.mapper;

import com.hao.pojo.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //运行时，框架自动自动生成该接口的实现类对象（代理对象）并将该对象交给IOC容器管理
public interface TestMapper {

    //获取测试数据
    @Select("select * from test")
    public List<Test> listTest();
}
