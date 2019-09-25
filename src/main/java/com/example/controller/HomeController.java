package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ConcurrentHashMap;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index() {
        return "index";
    }

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("aaa", "aaa");
        concurrentHashMap.put("aaa", "bbb");
        concurrentHashMap.put("aaa1", "ccc");
        concurrentHashMap.get("");
        concurrentHashMap.get("aaa");

    }

}
