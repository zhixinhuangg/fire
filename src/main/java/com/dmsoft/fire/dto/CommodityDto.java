package com.dmsoft.fire.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhixin.huang
 * @date 2019/4/8 18:30
 */
@Data
public class CommodityDto {

    private String commodityNo;

    private String commodityName;

    private String commodityType;

    private String commodityPrice;

    private String commodityDesc;
}
