package com.dmsoft.fire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhixin.huang
 * @date 2019/4/8 18:30
 */
@Data
@ApiModel("商品")
public class CommodityDto {

    @ApiModelProperty("商品编号")
    private String commodityNo;

    @ApiModelProperty("商品名称")
    private String commodityName;

    @ApiModelProperty("商品类别")
    private String commodityType;

    @ApiModelProperty("商品价格")
    private String commodityPrice;

    @ApiModelProperty("商品描述")
    private String commodityDesc;
}
