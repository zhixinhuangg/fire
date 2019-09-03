package com.dmsoft.fire.openapi.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhixin.huang
 * @date 2019/4/9 13:55
 */
@Data
public class BusinessException extends RuntimeException implements Serializable {
    public static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
