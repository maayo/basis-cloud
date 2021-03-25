package com.basis.cloud.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础类
 */
@Setter
@Getter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5563218739093882599L;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除
     * 0：正常；1：删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /**
     * 逻辑删除信息
     */
    private String deleteInfo;
}
