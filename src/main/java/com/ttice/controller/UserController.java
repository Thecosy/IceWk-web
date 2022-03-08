package com.ttice.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.Util.JwtUtil;
import com.ttice.commin.lang.Result;
import com.ttice.entity.User;
import com.ttice.mapper.UserMapper;
import com.ttice.service.UserService;
import com.ttice.commin.vo.UserNameVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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
    @GetMapping("/login")//登陆
    public Result login(User user) {
        //进行登陆核验操作
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //用户名判断
        wrapper.eq("USERNAME", user.getUsername());
        User userjudje = userService.getOne(wrapper);
        Assert.notNull(user,"用户名不存在");
        if(!userjudje.getPassword().equals(user.getPassword())) {
            return Result.fail(("密码不正确"));
        }
        //添加token
        String token = JwtUtil.createToken(userjudje.getUserId());
        user.setToken(token);
        //根据userid获取QueryWrapper对象
        QueryWrapper<User> wrappertoken = new QueryWrapper<>();
        wrappertoken.eq("user_id", userjudje.getUserId());
        //实体类
        User doc = new User();
        doc.setToken(token);
        //new Date()更新登陆时间
        doc.setLastLogin(new Date());
        //这一步进行成功之后在数据库保存生成的token操作
        userService.update(doc,wrappertoken);
        //返回状态
        return Result.succ(200,"成功登陆",token);
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
