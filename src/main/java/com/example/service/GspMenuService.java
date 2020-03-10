package com.example.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.model.base.ResponseVo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;

/**
 * Created by finup on 2018/12/11.
 */
@Service
@Slf4j
public class GspMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GspMenuService.class);

    @Autowired
    private GspMenuMapper gspMenuMapper;

    @Autowired
    private GspMenu1Service gspMenu1Service;

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


    /**
     * 事务测试
     */
    @Transactional(rollbackFor = Exception.class, timeout = 3)
    public void addGspMenu(){
        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("A");
        gspMenuMapper.insert(gspMenu);

        gspMenu1Service.addGspMenu1();
//        this.addGspMenu1();
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        gspMenu.setMenuName("B");
        gspMenuMapper.insert(gspMenu);

//        throw new RuntimeException();

    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addGspMenu1(){
        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("C");
        gspMenuMapper.insert(gspMenu);
        throw new RuntimeException();
    }


    //cas 数据库操作
    @Transactional(rollbackFor = Exception.class)
    public void updateGspMenu(){
        int result = 0;
        Long id = 14L;
        GspMenu gm = gspMenuMapper.selectById(id);
        LOGGER.info("第一次查询信息:{}", gm);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("1111");
        gspMenu.setMenuFatherId(gm.getMenuFatherId() + 1);
        while (result != 1){
            LOGGER.info("修改失败开始循环");
            result = gspMenuMapper.update(gspMenu, new UpdateWrapper<GspMenu>().lambda().eq(GspMenu::getId, id).eq(GspMenu::getMenuFatherId, gm.getMenuFatherId()));
            if (result != 1){
                gm = gspMenu1Service.findGspMenu(id);
                LOGGER.info("第二次查询信息:{}", gm);

            }
//            result = gspMenu1Service.newUpdateGspMenu(id);
//            LOGGER.info("第二次更新结果:{}", result);
        }
        LOGGER.info("修改成功退出循环");
    }


}
