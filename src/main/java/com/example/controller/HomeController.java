package com.example.controller;

import com.example.model.GspRole;
import com.example.model.base.ResponseVo;
import com.example.service.HomeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gsp/role")
public class HomeController {

    @Autowired
    private HomeService homeService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/v1/add")
    @ApiOperation(value = "新增角色", httpMethod = "POST", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<String> findGspMenuPost(@RequestBody GspRole gspRole){
        return homeService.add(gspRole);
    }

    @GetMapping("/v1/find/{id}")
    @ApiOperation(value = "查询信息", httpMethod = "GET", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<GspRole> find(@RequestParam(name = "id") Long id){
        return homeService.find(id);
    }

}
