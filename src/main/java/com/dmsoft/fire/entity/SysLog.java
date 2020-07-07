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
 * Table: sysLog
 * 
 * @author zhixin.huang
 * @date 2020/07/07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sysLog")
public class SysLog extends Model<SysLog> {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 
     */
    @TableField(value = "url")
    private String url;

    /**
     * 
     */
    @TableField(value = "content")
    private String content;

    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}