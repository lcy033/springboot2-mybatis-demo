package com.example.demo;

import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class DemoApplicationTests {

	@Autowired
	private GspMenuMapper gspMenuMapper;

	@Autowired
	private ExecutorService executorService;

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
		for (int i = 0; i < 30; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程启动" );
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

}
