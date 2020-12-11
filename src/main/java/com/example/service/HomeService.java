package com.example.service;

import com.example.mapper.GspRoleMapper;
import com.example.model.GspRole;
import com.example.model.base.ResponseVo;
import com.example.utils.Base64Util;
import com.example.utils.QRCodeUtils;
import jodd.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class HomeService {

    @Autowired
    private GspRoleMapper gspRoleMapper;
    @Autowired
    DocumentationCache documentationCache;
    @Autowired
    ServiceModelToSwagger2Mapper mapper;

    public ResponseVo<String> add(GspRole gspRole) {
        gspRoleMapper.insert(gspRole);
        return ResponseVo.ofSuccess();
    }

    public ResponseVo<GspRole> find(Long id) {
        return ResponseVo.ofSuccess(gspRoleMapper.selectById(id));
    }

    public ResponseVo<Object> findApi() {

return null;
    }

    public static String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Base64Util.decoder(data);
    }


    public static void main(String[] args) {
        URL uri = null;
        try {
            uri = new URL("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHU8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyd1E1MzVfNWtjTkUxTk11VTF2Y3cAAgRwkdBfAwQAjScA");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = uri.openStream();
            System.out.println(HomeService.getBase64FromInputStream(in));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<String> listImgSrc = new ArrayList<>();
        listImgSrc.add("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHU8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyd1E1MzVfNWtjTkUxTk11VTF2Y3cAAgRwkdBfAwQAjScA");
        HomeService.download(listImgSrc);

    }


    //下载图片
    private static void download(List<String> listImgSrc) {
        try {
            //开始时间
            Date begindate = new Date();
            for (String url : listImgSrc) {
                if (url.endsWith("jpg") && url.endsWith("png")){
                    continue;
                }
                //开始时间
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1);
                String path = "/Users/finup/Desktop/png/" + imageName;
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File(path));
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");

                //筛选二维码
                String content = QRCodeUtils.deEncodeByPath(path);
                if (content != null){
                    String path1 = "/Users/finup/Desktop/er/" + imageName;
                    FileUtil.copy(path, path1);
                }
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
            System.out.println("下载失败");
        }
    }
}
