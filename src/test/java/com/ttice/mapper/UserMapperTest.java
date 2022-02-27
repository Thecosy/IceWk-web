package com.ttice.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.entity.User;
import com.ttice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //用户名判断
        wrapper.eq("USERNAME", "admin");
        //密码判断
        wrapper.eq("PASSWORD", "1231234");
        User judjeadmin = userService.getOne(wrapper);
        if (judjeadmin == null) {
            System.out.println("no");
        }
        else {
            //这一步进行成功之后在数据库保存生成的tokenid操作
            System.out.println("ok");
            System.out.println(judjeadmin.getUserId());
            //根据userid获取QueryWrapper对象
            QueryWrapper<User> wrappertoken = new QueryWrapper<>();
            wrappertoken.eq("user_id", judjeadmin.getUserId());
            //实体类
            User doc = new User();
            doc.setToken("1234567");
            //更新token
            userService.update(doc,wrappertoken);
        }
    }

}
