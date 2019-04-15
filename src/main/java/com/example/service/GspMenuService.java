package com.example.service;

import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.model.base.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by finup on 2018/12/11.
 */
@Service
public class GspMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GspMenuService.class);

    @Autowired
    private GspMenuMapper gspMenuMapper;

    /**
     * 查找数据
     * @param id
     * @return
     */
    public ResponseVo<GspMenu> findGspMenu(Long id) {
        GspMenu gspMenu = gspMenuMapper.findGspMenuById(id);
        if (gspMenu == null){
            return ResponseVo.ofError();
        }
        return ResponseVo.ofSuccess(gspMenu);
    }
}
