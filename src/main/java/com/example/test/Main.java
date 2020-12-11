package com.example.test;

import cn.hutool.http.HttpUtil;
import com.example.threadPollExecutorCase.MyIgnorePolicy;
import com.example.threadPollExecutorCase.NameTreadFactory;
import com.example.utils.QRCodeUtils;
import jodd.io.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // 地址
    private static final String URL_INDEX = "http://www.jzb.com/bbs/forum-1502-1.html";
    // 获取a标签正则
    private static final String A_REG = "<a.*href=(.*?)thread-[^>]*?>";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*file=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";


    public static void main(String[] args) {
        try {
            int corePoolSize = 5;
            int maximumPoolSize = 10;
            long keepAliveTime = 30;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
            ThreadFactory threadFactory = new NameTreadFactory();
            RejectedExecutionHandler handler = new MyIgnorePolicy();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                    workQueue, threadFactory, handler);
            executor.prestartAllCoreThreads(); // 预启动所有核心线程

            Main cm = new Main();
            for (int i = 1; i < 15; i++) {
                int finalI = i;
                executor.execute(()->{
                    String urlIndex = "http://www.jzb.com/bbs/forum-809-"+ finalI + ".html";
                    System.out.println(urlIndex);
                    //获得html文本内容 首页
                    String html1 = cm.getHtml(urlIndex);
                    //获取A标签
                    List<String> aUrl = cm.getAUrl(html1);
                    //获取a标签url
                    List<String> aSrc = cm.getImageSrc(aUrl);
                    for (String s : aSrc){
                        if (!s.endsWith("html")){
                            continue;
                        }
                        //获得html文本内容
                        String html = cm.getHtml(s);
                        //获取当前页面图片标签
                        List<String> imgUrl = cm.getImageUrl(html);
                        //获取当前页面A标签 分页
                        List<String> a1Url = cm.getAUrl(html);
                        List<String> aSrc2 = cm.getImageSrc(a1Url);
                        for (String s1 : aSrc2){
                            if (!s.endsWith("html")){
                                continue;
                            }
                            //获得html文本内容
                            String html2 = cm.getHtml(s1);
                            List<String> imgUrl1 = cm.getImageUrl(html2);
                            if (!imgUrl1.isEmpty()){
                                imgUrl.addAll(imgUrl1);
                            }
                        }

                        //获取图片src地址
                        List<String> imgSrc = cm.getImageSrc(imgUrl);
                        //下载图片
                        cm.download(imgSrc);
                        System.out.println(s);
                    }
                });
            }

        } catch (Exception e) {
            System.out.println("发生错误");
        }

    }

    //获取HTML内容
    private String getHtml(String url) {
        return HttpUtil.get(url);
    }

    //获取帖子地址
    private List<String> getAUrl(String html) {
        Matcher matcher = Pattern.compile(A_REG).matcher(html);
        List<String> listimgurl = new ArrayList<>();
        while (matcher.find()) {
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //获取ImageUrl地址
    private List<String> getImageUrl(String html) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
        List<String> listimgurl = new ArrayList<>();
        while (matcher.find()) {
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //获取ImageSrc地址
    private List<String> getImageSrc(List<String> listimageurl) {
        List<String> listImageSrc = new ArrayList<>();
        for (String image : listimageurl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()) {
                listImageSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
            }
        }
        return listImageSrc;
    }

    //下载图片
    private void download(List<String> listImgSrc) {
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
