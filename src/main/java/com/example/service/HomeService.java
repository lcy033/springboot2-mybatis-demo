package com.example.service;

import com.example.mapper.GspRoleMapper;
import com.example.model.GspMenu;
import com.example.model.GspRole;
import com.example.model.base.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HomeService {

    @Autowired
    private GspRoleMapper gspRoleMapper;

    public ResponseVo<String> add(GspRole gspRole) {
        gspRoleMapper.insert(gspRole);
        return ResponseVo.ofSuccess();
    }

    public ResponseVo<GspRole> find(Long id) {
        return ResponseVo.ofSuccess(gspRoleMapper.selectById(id));
    }
}
