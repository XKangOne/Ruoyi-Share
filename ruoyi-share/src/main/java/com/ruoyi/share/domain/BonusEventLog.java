package com.ruoyi.share.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 积分变更记录对象 bonus_event_log
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("bonus_event_log")
@ApiModel(value = "BonusEventLog", description = "积分变更记录实体")
public class BonusEventLog
{
    private static final long serialVersionUID = 1L;

    /** Id */
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    @ApiModelProperty("用户id")
    private Long userId;

    /** 积分操作值 */
    @Excel(name = "积分操作值")
    @ApiModelProperty("积分操作值")
    private Integer value;

    /** 积分事件（签到、投稿、兑换等） */
    @Excel(name = "积分事件", readConverterExp = "签=到、投稿、兑换等")
    @ApiModelProperty("积分事件（签到、投稿、兑换等）")
    private String event;

    /** 描述 */
    @Excel(name = "描述")
    @ApiModelProperty("描述")
    private String description;

    @Excel(name = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

}
