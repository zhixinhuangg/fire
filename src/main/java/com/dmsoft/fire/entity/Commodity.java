package com.dmsoft.fire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Table: commodity
 * 
 * @author zhixin.huang
 * @date 2020/07/07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("commodity")
public class Commodity extends Model<Commodity> {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "commodity_no")
    private String commodityNo;

    /**
     * 
     */
    @TableField(value = "commodity_name")
    private String commodityName;

    /**
     * 
     */
    @TableField(value = "commodity_type")
    private String commodityType;

    /**
     * 
     */
    @TableField(value = "commodity_price")
    private String commodityPrice;

    /**
     * 
     */
    @TableField(value = "commodity_desc")
    private String commodityDesc;

    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}