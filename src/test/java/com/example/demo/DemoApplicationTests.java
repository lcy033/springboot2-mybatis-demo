package com.example.demo;

import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.service.AsyncService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;

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
