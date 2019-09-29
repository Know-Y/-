package com.api.user.mapper;

import com.api.user.entity.User;
import com.api.expand.unit.Query;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectUser(@Param("ew") Query query);
    List<User> Login(String user,String pass);
}
