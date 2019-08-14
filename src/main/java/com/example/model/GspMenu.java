package com.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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
public class GspMenu implements Serializable {

    @ApiModelProperty(value = "id", required = true)
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
