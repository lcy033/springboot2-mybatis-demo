package com.example.controller;

import com.example.framework.RedisService;
import com.example.model.base.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by finup on 2018/12/11.
 */
@RestController
@RequestMapping("/api/test")
@Slf4j
@Api(tags = "02.测试")
public class TestController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/v1/add")
    @ApiOperation(value = "操作", httpMethod = "POST", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<String> test() {
        String date = "date";
        String num = redisService.get(date);
        Integer number = Integer.parseInt(num) - 1;
        if (number == -1) {
            return ResponseVo.ofSuccess("没货了");
        }
        redisService.setString(date, number.toString());
        log.info("当前库存:{}", number);
        return ResponseVo.ofSuccess("服务一切正常");
    }

}
