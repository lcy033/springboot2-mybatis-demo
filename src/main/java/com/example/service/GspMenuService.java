package com.example.service;

import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.model.base.ResponseVo;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * 事务测试
     */
    @Transactional(rollbackFor = Exception.class, timeout = 3)
    public void addGspMenu(){
        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("A");
        gspMenuMapper.insert(gspMenu);

//        this.addGspMenu1();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gspMenu.setMenuName("B");
        gspMenuMapper.insert(gspMenu);

//        throw new RuntimeException();

    }


//    @Transactional(rollbackFor = Exception.class)
    public void addGspMenu1(){
        GspMenu gspMenu = new GspMenu();
        gspMenu.setMenuName("C");
        gspMenuMapper.insert(gspMenu);
//        throw new RuntimeException();
    }
}
