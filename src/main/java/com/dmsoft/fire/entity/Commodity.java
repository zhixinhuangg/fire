package com.dmsoft.fire.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhixin.huang
 * @date 2019/4/8 17:41
 */
@Data
@Entity
@Table(name = "t_commodity")
public class Commodity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String commodityNo;

    private String commodityName;

    private String commodityType;

    private String commodityPrice;

    private String commodityDesc;

}
