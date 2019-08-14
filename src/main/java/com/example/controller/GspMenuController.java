package com.example.controller;

import com.example.model.GspMenu;
import com.example.model.base.ResponseVo;
import com.example.service.AsyncService;
import com.example.service.GspMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by finup on 2018/12/11.
 */
@RestController
@RequestMapping("/api/gsp")
@Api(tags = "01.gsp测试1")
public class GspMenuController {

    @Autowired
    private GspMenuService gspMenuService;
    @Autowired
    private AsyncService asyncService;

    @PostMapping("/v1/find")
    @ApiOperation(value = "查询信息", httpMethod = "POST", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<GspMenu> findGspMenuPost(@RequestBody GspMenu gspMenu){
        return gspMenuService.findGspMenu(gspMenu.getId());
    }

    @GetMapping("/v1/find/{id}")
    @ApiOperation(value = "查询信息", httpMethod = "GET", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<GspMenu> findGspMenuGet(@RequestParam(name = "id") Long id){
        return gspMenuService.findGspMenu(id);
    }

    @GetMapping("/v1/async")
    @ApiOperation(value = "异步线程池提交任务", httpMethod = "GET", produces = "application/json;charset=UTF-8")
    public void async(){
        asyncService.executeAsync();
    }
}
