package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.GspMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by finup on 2018/12/10.
 */
@Mapper
public interface GspMenuMapper extends BaseMapper<GspMenu>{

    /**
     * 查询
     * @param id
     * @return
     */
    GspMenu findGspMenuById(@Param("id") Long id);
}
