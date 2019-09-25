package com.example.demo;

import com.example.DemoApplication;
import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.service.AsyncService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

	@Autowired
	private GspMenuMapper gspMenuMapper;

	@Autowired
	private ExecutorService executorService;

	@Autowired
	private AsyncService asyncService;

	@Test
	public void contextLoads() {
		ObjectMapper objectMapper = new ObjectMapper();
		GspMenu gspMenu = gspMenuMapper.findGspMenuById(1L);
		try {
			System.out.println(objectMapper.writeValueAsString(gspMenu));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ThreadTest() {

		//消费队列
		for (int i = 0; i < 15; i++) {
			executorService.execute(() -> {
                System.out.println("线程启动" );
				asyncService.executeAsync();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
				System.out.println("线程结束" );
            });
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
