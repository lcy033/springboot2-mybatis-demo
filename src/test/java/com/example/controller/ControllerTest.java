package com.example.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void find() throws Exception {
        RequestBuilder request = get("/gsp/role/v1/find/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", "1");
        mvc.perform(request).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void add() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "测试");
        map.put("roleType", "TEST");
        map.put("roleDesc", "测试测试");
        RequestBuilder request = MockMvcRequestBuilders.post("/gsp/role/v1/add").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONBytes(map));
        mvc.perform(request).andExpect(status().isOk()).andDo(print());
    }

}
