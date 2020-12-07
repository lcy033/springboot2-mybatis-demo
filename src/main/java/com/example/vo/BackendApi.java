package com.example.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ApiModel("接口vo")
@Setter
@Getter
@ToString
public class BackendApi implements Serializable {
    private Long id;

    private String tag;

    private String path;

    private String method;

    private String summary;

    private String operationId;

}
