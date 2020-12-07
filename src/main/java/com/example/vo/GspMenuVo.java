package com.example.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by finup on 2018/12/10.
 */
public class GspMenuVo {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
