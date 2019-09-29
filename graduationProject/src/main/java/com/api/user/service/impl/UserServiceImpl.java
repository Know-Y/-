package com.api.user.service.impl;

import com.api.expand.unit.Result;
import com.api.user.entity.User;
import com.api.user.mapper.UserMapper;
import com.api.user.service.IUserService;
import com.api.expand.unit.Query;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result selectUser(Query query) {
        List<User> dataList = userMapper.selectUser(query.make());
        return Result.success(dataList);
    }

    @Override
    public List<User> Login(String user,String pass){
        List<User> list = userMapper.Login(user,pass);
        return list;
    }
}
