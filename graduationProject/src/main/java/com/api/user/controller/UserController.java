package com.api.user.controller;


import com.api.expand.unit.Result;
import com.api.user.entity.User;
import com.api.user.mapper.UserMapper;
import com.api.user.service.IUserService;
import com.api.expand.unit.JwtToken;
import com.api.expand.unit.Query;
import com.api.expand.unit.VerificationCode;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController{
    @Resource
    IUserService iUserService;
    @Resource
    UserMapper userMapper;

    @GetMapping
    public Result selectUser(Query query) {
        return iUserService.selectUser(query);
    }

    @PostMapping
    public Result insertUser(@RequestBody User user){
        iUserService.save(user);
        return Result.success();
    }

    @PatchMapping(value = "findpass")
    public Result updateUserPass(@RequestBody User user){
        userMapper.update(user,new UpdateWrapper<User>().eq("phone_number",user.getPhone_number()));
        return Result.success();
    }

    @GetMapping(value = "validates")
    public String verificationCode(String phone){
        VerificationCode vcode = new VerificationCode();
        String code = vcode.sendCode(phone);
        return code;
    }

    @GetMapping(value = "login")
    public String Login(String user,String password){
        List<User> list = iUserService.Login(user,password);
        JwtToken jwtToken = new JwtToken();
        String token = null;
        try {
            token=jwtToken.createToken(list.get(0).getUser_id(),list.get(0).getUser_name(),list.get(0).getPhone_number());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return token;
    }
}
