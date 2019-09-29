package com.api.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("user")
public class User implements Serializable {

    @TableId(type = IdType.UUID)
    private String user_id;
    private String user_name;
    private String user_avatar;
    private String phone_number;
    private String password;
    private Integer sex;
    private String city;
    private String intro;
    private Integer fans;
    private Integer followers;
    private Date created_at;
}
