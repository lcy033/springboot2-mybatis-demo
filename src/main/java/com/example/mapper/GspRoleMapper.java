package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aop.annotation.DecodeBase64;
import com.example.aop.annotation.EncodeBase64;
import com.example.model.GspMenu;
import com.example.model.GspRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GspRoleMapper extends BaseMapper<GspRole>{

}
