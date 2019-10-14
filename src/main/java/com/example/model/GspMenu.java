package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by finup on 2018/12/10.
 */
@ApiModel("测试vo")
@Data
@Setter
@Getter
@ToString
@TableName(value = "gsp_menu")
public class GspMenu implements Serializable {

    @ApiModelProperty(value = "id", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "name")
    private String menuName;

    @ApiModelProperty(value = "url")
    private String menuUrl;

    @ApiModelProperty(value = "父ID")
    private Integer menuFatherId;

    @ApiModelProperty(value = "测试")
    private String menuDesc;

    @ApiModelProperty(value = "测试")
    private Boolean enable;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
