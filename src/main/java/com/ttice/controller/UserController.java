package com.ttice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.Util.JwtUtil;
import com.ttice.entity.Article;
import com.ttice.entity.User;
import com.ttice.mapper.UserMapper;
import com.ttice.service.UserService;
import com.ttice.vo.ArticleVO;
import com.ttice.vo.UserNameVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@io.swagger.annotations.Api(tags = "用户登陆验证接口")
@RestController
public class UserController {

    //数据库账号
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "登陆")
    @ApiImplicitParam(name = "user",value = "user对象",required = true)
    @GetMapping("/login")//登陆
    public User login(User user){
        //进行登陆核验操作
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //用户名判断
        wrapper.eq("USERNAME", user.getUsername());
        //密码判断
        wrapper.eq("PASSWORD", user.getPassword());
        User judjeadmin = userService.getOne(wrapper);

        if (judjeadmin == null) {
            return null;
        }
        else {
            //添加token
            String token = JwtUtil.createToken();
            user.setToken(token);

            //这一步进行成功之后在数据库保存生成的tokenid操作
            System.out.println("ok");
            System.out.println(judjeadmin.getUserId());
            //根据userid获取QueryWrapper对象
            QueryWrapper<User> wrappertoken = new QueryWrapper<>();
            wrappertoken.eq("user_id", judjeadmin.getUserId());
            //实体类
            User doc = new User();
            doc.setToken(token);
            //更新token
            userService.update(doc,wrappertoken);
            //返回状态
            return user;
        }
    }

    @ApiOperation(value = "验证Token")
    @ApiImplicitParam(name = "request",value = "header里的token值",required = true)
    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }

    @ApiOperation(value = "获取全部用户名称")
    @GetMapping("/getAllUserName")
    public List<UserNameVO> getAllUserName(){
        List<UserNameVO> result = new ArrayList<>();

        QueryWrapper<User> wrapper= new QueryWrapper<User>();
        wrapper.select("username");
        UserNameVO userNameVO = null;
        List<User> users = userMapper.selectList(wrapper);
        for (User user1 : users) {
            userNameVO = new UserNameVO();
            BeanUtils.copyProperties(user1,userNameVO);
            result.add(userNameVO);
        }

        return result;
    }
}
