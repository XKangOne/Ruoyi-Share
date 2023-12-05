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
 * 分享对象 user
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User", description = "分享实体")
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Id */
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** 手机号 */
    @Excel(name = "手机号")
    @ApiModelProperty("手机号")
    private String phone;

    /** 密码 */
    @Excel(name = "密码")
    @ApiModelProperty("密码")
    private String password;

    /** 昵称 */
    @Excel(name = "昵称")
    @ApiModelProperty("昵称")
    private String nickname;

    /** 角色 */
    @Excel(name = "角色")
    @ApiModelProperty("角色")
    private String roles;

    /** 头像地址 */
    @Excel(name = "头像地址")
    @ApiModelProperty("头像地址")
    private String avatarUrl;

    /** 积分 */
    @Excel(name = "积分")
    @ApiModelProperty("积分")
    private Long bonus;

}
