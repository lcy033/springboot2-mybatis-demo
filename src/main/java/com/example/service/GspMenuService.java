package com.example.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.model.base.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

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
    private ThreadLocal<Long> number = new ThreadLocal<>();

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
    public ResponseVo<String> updateGspMenu(){
        int result = 0;
        Long id = 14L;
        GspMenu gm = gspMenuMapper.selectById(id);
        LOGGER.info("第一次查询信息:{}", gm);
        try {
            Thread.sleep(1000 + RandomUtils.nextLong(100L, 1000L));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("1111");
        while (result != 1){
            number.set(0L);
            LOGGER.info("修改失败开始循环");
            gspMenu.setMenuFatherId(gm.getMenuFatherId() + 1);
            result = gspMenuMapper.update(gspMenu, new UpdateWrapper<GspMenu>().lambda().eq(GspMenu::getId, id).eq(GspMenu::getMenuFatherId, gm.getMenuFatherId()));
            if (result != 1){

                try {
                    number.set(number.get() + 1000L + RandomUtils.nextLong(100L, 1000L));
                    Thread.sleep(number.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                gm = gspMenu1Service.findGspMenu(id);
                LOGGER.info("第二次查询信息:{}", gm);
            }
//            result = gspMenu1Service.newUpdateGspMenu(id);
//            LOGGER.info("第二次更新结果:{}", result);
        }
//        gm = gspMenu1Service.findGspMenu1(id);
        number.remove();
        LOGGER.info("修改成功退出循环");
        return ResponseVo.ofSuccess();
    }

    /**
     * 加密仍数据库测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new BASE64Encoder().encode(String.valueOf("用户管理").getBytes()));
        System.out.println(new BASE64Encoder().encode(String.valueOf("角色管理").getBytes()));
        System.out.println(new BASE64Encoder().encode(String.valueOf("测试3").getBytes()));
        System.out.println(new BASE64Encoder().encode(String.valueOf("测试2").getBytes()));
        System.out.println(new BASE64Encoder().encode(String.valueOf("1111").getBytes()));
    }


}
