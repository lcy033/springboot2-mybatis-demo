package com.example.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@ApiModel("ExcelVo")
public class ExcelVo implements Serializable {

    @ApiModelProperty(value = "ID")
    @Excel(name = "ID", orderNum = "15")
    private Long id;

    @Excel(name = "姓名", orderNum = "15")
    private String name;

}
