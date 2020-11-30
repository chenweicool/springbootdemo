package com.security.exception;

import lombok.Getter;

/**
 * 自定义异常的实现
 */
@Getter
public class CustomException extends RuntimeException {

      private int code;
      private String msg;

    public CustomException(CustomType customType) {
        this.code = customType.getCode();
        this.msg = customType.getDescription();
    }

    public CustomException(CustomType customType, String message) {
        this.code = customType.getCode();
        this.msg = message;
    }

    public String getMessage(){
        return msg;
    }

}
