package com.api.expand.unit;

public enum ErrorCode {
    E0("成功"),
    E1("系统错误"),
    E2("系统繁忙"),
    E3("服务未找到"),
    E4("网关异常"),
    E5("参数错误"),
    E6("上传文件大小超过限制"),
    E7("认证错误"),
    E1000("用户名密码错误"),
    E1001("用户未启用"),
    E1002("用户被锁定"),
    E1003("用户已过期"),
    E1004("已存在"),
    E1005("用户不存在"),
    E1006("已是最新"),
    E1007("状态有误"),
    E1008("用户名密码错误");

    private String desc;

    public int val() {
        return Integer.parseInt(this.name().substring(1));
    }

    public String desc() {
        return this.desc;
    }

    private ErrorCode(String desc) {
        this.desc = desc;
    }
}
