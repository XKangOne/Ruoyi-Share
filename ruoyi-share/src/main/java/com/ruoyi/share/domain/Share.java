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
 * 分享对象 share
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("share")
@ApiModel(value = "Share", description = "分享实体")
public class Share extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** 发布人id */
    @Excel(name = "发布人id")
    @ApiModelProperty("发布人id")
//    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /** 标题 */
    @Excel(name = "标题")
    @ApiModelProperty("标题")
    private String title;

    /** 是否原创 0:否 1:是 */
    @Excel(name = "是否原创 0:否 1:是")
    @ApiModelProperty("是否原创 0:否 1:是")
    private Integer isOriginal;

    /** 作者 */
    @Excel(name = "作者")
    @ApiModelProperty("作者")
    private String author;

    /** 封面 */
    @Excel(name = "封面")
    @ApiModelProperty("封面")
    private String cover;

    /** 概要信息 */
    @Excel(name = "概要信息")
    @ApiModelProperty("概要信息")
    private String summary;

    /** 价格（需要的积分） */
    @Excel(name = "价格", readConverterExp = "需=要的积分")
    @ApiModelProperty("价格（需要的积分）")
    private Long price;

    /** 下载地址 */
    @Excel(name = "下载地址")
    @ApiModelProperty("下载地址")
    private String downloadUrl;

    /** 下载数  */
    @Excel(name = "下载数 ")
    @ApiModelProperty("下载数 ")
    private Long buyCount;

    /** 是否显示 0:否 1:是 */
    @Excel(name = "是否显示 0:否 1:是")
    @ApiModelProperty("是否显示 0:否 1:是")
    private Integer showFlag;

    /** 审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过 */
    @Excel(name = "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过")
    @ApiModelProperty("审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过")
    private String auditStatus;

    /** 审核不通过原因 */
    @Excel(name = "审核不通过原因")
    @ApiModelProperty("审核不通过原因")
    private String reason;

}
