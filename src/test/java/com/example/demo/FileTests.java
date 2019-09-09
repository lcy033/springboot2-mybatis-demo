package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import com.example.utils.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileTests {

	@Autowired
	private GspMenuMapper gspMenuMapper;

	@Test
	public void contextLoads() {

		try {

			//new一个URL对象
			URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545815535047&di=26dd42ed8cf3b5e10058d6e3743f731b&imgtype=0&src=http%3A%2F%2Fwww.17qq.com%2Fimg_biaoqing%2F44681694.jpeg");
			//打开链接
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			//设置请求方式为"GET"
			conn.setRequestMethod("GET");
			//超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			//通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			//得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			//new一个文件对象用来保存图片，默认保存当前工程根目录
			File imageFile = new File("BeautyGirl.jpg");
			//创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			//写入数据
			outStream.write(data);
			//关闭输出流
			outStream.close();

		}catch (Exception e){

		}
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while( (len=inStream.read(buffer)) != -1 ){
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	@Test
	public void fileTest() {

		byte[] b;
		try {

			URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545815535047&di=26dd42ed8cf3b5e10058d6e3743f731b&imgtype=0&src=http%3A%2F%2Fwww.17qq.com%2Fimg_biaoqing%2F44681694.jpeg");
			File file = FileUtils.toFile(url);
			InputStream in = new FileInputStream(file);
			b = new byte[(int) file.length()];
			in.read(b);    //读取文件中的内容到b[]数组

			File imageFile = new File("BeautyGirl.jpg");
			//创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			//写入数据
			outStream.write(b);
			//关闭输出流
			outStream.close();
		}catch (Exception e){
			System.out.println(e);
		}
	}

	@Test
	public void testMybatisPlus(){
		List<GspMenu> list = gspMenuMapper.selectList(null);
		log.info("结果：{}", list);
	}

	@Test
	@Transactional(rollbackFor = Exception.class)
	public void testMySql(){
		GspMenu gspMenu = gspMenuMapper.selectById(6L);
		log.info("结果1：{}", gspMenu);
		gspMenu.setMenuDesc("10");
		gspMenuMapper.updateById(gspMenu);
		GspMenu gspMenu1 = gspMenuMapper.selectById(6L);
		log.info("结果2：{}", gspMenu1);
	}

	public static void main(String[] args) {
		List<Map<String, String>> arrayList = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("s", "s");
		arrayList.add(map);
		Map<String, String> map1 = new HashMap<>();
		map1.put("s1", "s1");
		arrayList.add(map1);

//		JSONArray value = JSONObject.parseArray(JSON.toJSONString(arrayList));
		String value = JSON.toJSONString(arrayList);
		System.out.println("1:" + value);

		System.out.println(JSONObject.parseArray(value, Map.class));

	}

}
