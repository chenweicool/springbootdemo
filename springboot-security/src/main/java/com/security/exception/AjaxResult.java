package com.security.exception;

import lombok.Data;

/**
 * 定义的结果处理的异常类
 */
@Data
public class AjaxResult {
    /*是否处理成功*/
    private boolean isok;

    /*状态码*/
    private int code;

    /*响应的消息*/
    private String message;

    /*响应的数据实体*/
    private Object data;

    private AjaxResult() {
    }

    // 出错时的信息的提示
    public static AjaxResult error(CustomType customType, String message) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setIsok(false);
        ajaxResult.setCode(customType.getCode());
        ajaxResult.setMessage(message);
        return ajaxResult;
    }

    // 出现异常时的响应数据的封装
    public static AjaxResult error(CustomException e) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setIsok(false);
        ajaxResult.setCode(e.getCode());
        ajaxResult.setMessage(e.getMessage());
        return ajaxResult;
    }

    // 访问数据成功的处理，不带数据，主要是插入和删除和更改的响应信息
    public static AjaxResult success(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setIsok(true);
        ajaxResult.setCode(200);
        ajaxResult.setMessage("请求数据成功");
        return ajaxResult;
    }

    // 请求数据的成功的响应，带有数据的查询的实现
    public static AjaxResult success(Object object) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setIsok(true);
        ajaxResult.setCode(200);
        ajaxResult.setMessage("数据请求成功");
        ajaxResult.setData(object);
        return ajaxResult;
    }

    // 请求数据的成功的响应，带有查询数据的实现
    public static AjaxResult success(Object object, String message) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setIsok(true);
        ajaxResult.setMessage(message);
        ajaxResult.setCode(200);
        ajaxResult.setData(object);
        return ajaxResult;
    }
}
