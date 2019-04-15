package com.example.mapper;

import com.example.model.GspMenu;
import org.apache.ibatis.annotations.Param;

/**
 * Created by finup on 2018/12/10.
 */
public interface GspMenuMapper {

    /**
     * 查询
     * @param id
     * @return
     */
    GspMenu findGspMenuById(@Param("id") Long id);
}
