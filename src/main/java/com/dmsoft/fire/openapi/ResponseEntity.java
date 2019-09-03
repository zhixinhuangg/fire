package com.dmsoft.fire.openapi;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author zhixin.huang
 * @date 2019/4/8 18:30
 */
@Data
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    private String desc = "操作成功!";
    /**
     * is success
     */
    private boolean success = true;
    /**
     * data: { key1: value1, ... }
     */
    private T data;

    public ResponseEntity() {
    }

    private ResponseEntity(boolean success) {
        this.success = success;
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(int status) {
        this.status = status;
    }

    public ResponseEntity(T data, HttpStatus status) {
        this.data = data;
        this.status = status.value();
    }

    public ResponseEntity(T data, HttpStatus status, boolean success, String desc) {
        this.data = data;
        this.status = status.value();
        this.success = success;
        this.desc = desc;

    }

    public ResponseEntity(HttpStatus status, boolean success, String desc) {
        this.status = status.value();
        this.success = success;
        this.desc = desc;
    }

    public static ResponseEntity ofFailed() {
        return new ResponseEntity(false);
    }

    public static ResponseEntity ofSuccess() {
        return new ResponseEntity(true);
    }

    public ResponseEntity data(T data) {
        this.setData(data);
        return this;
    }

    public ResponseEntity desc(String desc) {
        this.setDesc(desc);
        return this;
    }

    public ResponseEntity status(HttpStatus status) {
        this.setStatus(status.value());
        return this;
    }

}
