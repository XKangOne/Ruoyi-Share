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
 * 信息对象 notice
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("notice")
@ApiModel(value = "Notice", description = "信息实体")
public class Notice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** 内容 */
    @Excel(name = "内容")
    @ApiModelProperty("内容")
    private String content;

    /** 是否显示 0:否 1:是 */
    @Excel(name = "是否显示 0:否 1:是")
    @ApiModelProperty("是否显示 0:否 1:是")
    private Integer showFlag;

}
