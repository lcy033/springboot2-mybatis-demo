package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aop.annotation.EncodeBase64;
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

    /**
     * 查询
     * @param menuName
     * @return
     */
    @EncodeBase64(value = "menuName")
    GspMenu findGspMenuByName(@Param("menuName") String menuName);

    /**
     * 查询
     * @param menuName
     * @param menuUrl
     * @return
     */
    @EncodeBase64(value = "menuName,menuUrl")
    GspMenu findGspMenuByName1(@Param("menuName") String menuName, @Param("menuUrl") String menuUrl);

    /**
     * 查询
     * @param menuName
     * @param menuFatherId
     * @return
     */
    @EncodeBase64(value = "menuName,menuUrl")
    GspMenu findGspMenuByName2(@Param("menuName") String menuName, @Param("menuFatherId") Integer menuFatherId);

    /**
     * 插入
     * @param gspMenu
     */
    @EncodeBase64(value = "menuName,menuUrl")
    void addGspMenu(@Param("pojo") GspMenu gspMenu);
}
