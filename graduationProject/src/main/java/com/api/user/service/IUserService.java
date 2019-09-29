package com.api.user.service;

import com.api.expand.unit.Result;
import com.api.user.entity.User;

import com.api.expand.unit.Query;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

public interface IUserService extends IService<User> {
    Result selectUser(Query query);
    List<User> Login(String user,String pass);
}
