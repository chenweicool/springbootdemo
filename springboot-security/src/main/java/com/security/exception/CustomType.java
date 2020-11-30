package com.security.exception;

import lombok.Getter;

/**
 * 定义异常的枚举类型
 */
@Getter
public enum CustomType {

    USER_INPUT_ERROR(400,"您的输入有误，请重新输入"),
    USER_NOT_EXIST(504,"该用户的信息不存在"),
    SYSTEM_ERROR(500, "系统的性的错误");
    private int code;
    private String description;

    CustomType(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
