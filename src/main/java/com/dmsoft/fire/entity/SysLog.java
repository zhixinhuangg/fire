package com.dmsoft.fire.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhixin.huang
 * @date 2019/6/20 9:14
 */
@Data
@Entity
@Table(name = "t_sysLog")
public class SysLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ip;

    private String url;

    private String content;
}
