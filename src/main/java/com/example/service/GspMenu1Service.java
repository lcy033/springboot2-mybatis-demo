package com.example.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.model.base.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by finup on 2018/12/11.
 */
@Service
public class GspMenu1Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(GspMenu1Service.class);

    @Autowired
    private GspMenuMapper gspMenuMapper;


//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addGspMenu1(){
        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("C");
        gspMenuMapper.insert(gspMenu);
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int newUpdateGspMenu(Long id) {
        GspMenu gm = gspMenuMapper.selectById(id);
        LOGGER.info("第二次查询信息:{}", gm);
        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("1111");
        gspMenu.setMenuFatherId(gm.getMenuFatherId() + 1);
        return gspMenuMapper.update(gspMenu, new UpdateWrapper<GspMenu>().lambda().eq(GspMenu::getId, id).eq(GspMenu::getMenuFatherId, gm.getMenuFatherId()));
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public GspMenu findGspMenu(Long id) {
        return gspMenuMapper.selectById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public GspMenu findGspMenu1(Long id) {
        return gspMenuMapper.selectById(id);
    }
}
