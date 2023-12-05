package com.ruoyi.share.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户-分享中间【描述用户购买的分享】对象 mid_user_share
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("mid_user_share")
@ApiModel(value = "MidUserShare", description = "用户-分享中间【描述用户购买的分享】实体")
public class MidUserShare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("$column.columnComment")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** share.id */
    @Excel(name = "share.id")
    @ApiModelProperty("share.id")
    private Long shareId;

    /** user.id */
    @Excel(name = "user.id")
    @ApiModelProperty("user.id")
    private Long userId;

}
