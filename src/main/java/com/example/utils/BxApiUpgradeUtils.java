package com.example.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @Description: Spring注入
 * @return:
 * @Author: lsf
 * @Date: 2021/1/25 14:48
 */
@Slf4j
@Service
public class BxApiUpgradeUtils {
    @Value("${bxApi.regUrl.base}")
    private String bxApiBaseUrl;
    @Autowired
    private RestTemplate restTemplate;
    private static final String partner = "10036";
    private static final String key = "!@$@@$!ss34";
//    private static final String headerKeyName = "key";
    private static final String headerSignName = "sign";
    private static final String headerParternerName = "partner";
    private static final String clientVersion = "2.1.0";
    private static final String headerClientVersionName = "winClientVersion";
    private static final String headerTokenName = "utoken";
    private static final String BX_LOGIN_SALT = "/reg/api/util/salt";

    /**
     * post，RestTemplate
     * @param param
     * @param requestUrl
     * @return
     */
    public JSONObject postRest(JSONObject param, String requestUrl){
        JSONObject result=new JSONObject();
        try {
            requestUrl = bxApiBaseUrl+requestUrl;
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(APPLICATION_JSON);
            requestHeaders.add(headerParternerName,partner);
//            requestHeaders.add(headerKeyName,key);
            requestHeaders.add(headerClientVersionName,clientVersion);
            requestHeaders.add(headerSignName,getSign(param.toJSONString()));
            HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(param, requestHeaders);
            log.info("请求CRM接口，postRest，begin，param={},url={}",param.toJSONString(),requestUrl);
            result= restTemplate.postForEntity(requestUrl,formEntity,JSONObject.class).getBody();
            log.info("请求CRM接口，postRest，end，param={},url={},返回结果={}",param.toJSONString(),requestUrl,result);
        }catch (Exception e){
            log.error("请求CRM接口，postRest异常，param={},url={},error={}",param.toJSONString(),requestUrl,e);
        }
        return result;
    }

    /**
     * post，RestTemplate
     * @param param
     * @param requestUrl
     * @return
     */
    public JSONObject postUrl(JSONObject param,String requestUrl){
        JSONObject result=new JSONObject();
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(APPLICATION_JSON);
            requestHeaders.add(headerParternerName,partner);
//            requestHeaders.add(headerKeyName,key);
            requestHeaders.add(headerClientVersionName,clientVersion);
            requestHeaders.add(headerSignName,getSign(param.toJSONString()));
            HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(param, requestHeaders);
            log.info("请求CRM接口，postUrl，begin，param={},url={}",param.toJSONString(),requestUrl);
            result= restTemplate.postForEntity(requestUrl,formEntity,JSONObject.class).getBody();
            log.info("请求CRM接口，postRest，end，param={},url={},返回结果={}",param.toJSONString(),requestUrl,result);
        }catch (Exception e){
            log.error("请求CRM接口，postUrl异常，param={},url={},error={}",param.toJSONString(),requestUrl,e);
        }
        return result;
    }

    /**
     * post，RestTemplate
     * @param param
     * @param requestUrl
     * @return
     */
    public JSONObject postToken(JSONObject param,String requestUrl,String token){
        JSONObject result=new JSONObject();
        try {
            requestUrl = bxApiBaseUrl+requestUrl;
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(APPLICATION_JSON);
            requestHeaders.add(headerParternerName,partner);
//            requestHeaders.add(headerKeyName,key);
            requestHeaders.add(headerClientVersionName,clientVersion);
            requestHeaders.add(headerTokenName,token);
            requestHeaders.add(headerSignName,getSignToken(token,param.toJSONString()));
            HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(param, requestHeaders);
            log.info("请求CRM接口，postToken，begin，param={},url={},token={}",param.toJSONString(),requestUrl,token);
            result= restTemplate.postForEntity(requestUrl,formEntity,JSONObject.class).getBody();
            log.info("请求CRM接口，postToken，end，param={},url={},token={},返回结果={}",param.toJSONString(),requestUrl,token,result);
        }catch (Exception e){
            log.error("请求CRM接口，postToken异常，param={},url={},token={},error={}",param.toJSONString(),requestUrl,token,e);
        }
        return result;
    }

    /**
     * post，RestTemplate
     * @param param
     * @param requestUrl
     * @return
     */
    public JSONObject postTokenUrl(JSONObject param,String requestUrl,String token){
        JSONObject result=new JSONObject();
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(APPLICATION_JSON);
            requestHeaders.add(headerParternerName,partner);
//            requestHeaders.add(headerKeyName,key);
            requestHeaders.add(headerClientVersionName,clientVersion);
            requestHeaders.add(headerTokenName,token);
            requestHeaders.add(headerSignName,getSignToken(token,param.toJSONString()));
            HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(param, requestHeaders);
            log.info("请求CRM接口，postTokenUrl，begin，param={},url={},token={}",param.toJSONString(),requestUrl,token);
            result= restTemplate.postForEntity(requestUrl,formEntity,JSONObject.class).getBody();
            log.info("请求CRM接口，postTokenUrl，end，param={},url={},token={},返回结果={}",param.toJSONString(),requestUrl,token,result);
        }catch (Exception e){
            log.error("请求CRM接口，postTokenUrl异常，param={},url={},token={},error={}",param.toJSONString(),requestUrl,token,e);
        }
        return result;
    }

    /**
     * get请求
     */
    public ResponseEntity<JSONObject> get(JSONObject param, String requestUrl){
        ResponseEntity<JSONObject> exchange=null;
        try {
            requestUrl = bxApiBaseUrl+requestUrl;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(APPLICATION_JSON);
            headers.set(headerParternerName,partner);
            headers.add(headerClientVersionName,clientVersion);
            headers.set(headerSignName,getSign());
            HttpEntity<JSONObject> entity = new HttpEntity<>(param, headers);
            log.info("请求CRM接口，get，begin，param={},url={}",param.toJSONString(),requestUrl);
            exchange = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, JSONObject.class);
            log.info("请求CRM接口，get，end，param={},url={},返回结果={}",param.toJSONString(),requestUrl,exchange);
        }catch (Exception e){
            log.error("请求CRM接口，get异常，param={},url={},error={}",param.toJSONString(),requestUrl,e);
        }
        return exchange;
    }

    /**
     * get请求
     */
    public ResponseEntity<JSONObject> getParam(JSONObject param,String requestUrl){
        ResponseEntity<JSONObject> exchange=null;
        try {
            requestUrl = bxApiBaseUrl+requestUrl;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(APPLICATION_JSON);
            headers.set(headerParternerName,partner);
            headers.add(headerClientVersionName,clientVersion);
            headers.set(headerSignName,getSignAsc(param));
            HttpEntity<JSONObject> entity = new HttpEntity<>(param, headers);
            log.info("请求CRM接口，getParam，begin，param={},url={}",param.toJSONString(),requestUrl);
            exchange = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, JSONObject.class);
            log.info("请求CRM接口，getParam，end，param={},url={},返回结果={}",param.toJSONString(),requestUrl,exchange);
        }catch (Exception e){
            log.error("请求CRM接口，getParam异常，param={},url={},error={}",param.toJSONString(),requestUrl,e);
        }
        return exchange;
    }

    /**
     * get请求
     */
    public ResponseEntity<JSONObject> getToken(JSONObject param,String requestUrl,String token){
        ResponseEntity<JSONObject> exchange=null;
        try {
            requestUrl = bxApiBaseUrl+requestUrl;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(APPLICATION_JSON);
            headers.set(headerParternerName,partner);
            headers.add(headerClientVersionName,clientVersion);
            headers.add(headerTokenName,token);
            headers.set(headerSignName,getSignAscToken(param,token));
            HttpEntity<JSONObject> entity = new HttpEntity<>(param, headers);
            log.info("请求CRM接口，getToken，begin，param={},url={},token={}",param.toJSONString(),requestUrl,token);
            exchange = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, JSONObject.class);
            log.info("请求CRM接口，getToken，end，param={},url={},token={},返回结果={}",param.toJSONString(),requestUrl,token,exchange);
        }catch (Exception e){
            log.error("请求CRM接口，getToken异常，param={},url={},token={},error={}",param.toJSONString(),requestUrl,token,e);
        }
        return exchange;
    }

    /**
     * post验签
     * @return
     */
    private static String getSign(String bodyContent){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("partner=")
                .append(partner)
                .append("&")
                .append(bodyContent)
                .append(key);
        String sign = Md5Utils.MD5_LOWERCASE(stringBuilder.toString());
        log.info("sign str == {}",stringBuilder.toString());
        log.info("sign == {}" , sign);
        return sign;
    }

    /**
     * post验签
     * @return
     */
    private String getSignToken(String token, String bodyContent){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("partner=")
                .append(partner)
                .append("&")
                .append("utoken=")
                .append(token)
                .append("&")
                .append(bodyContent)
                .append(key);
        String sign = Md5Utils.MD5_LOWERCASE(stringBuilder.toString());
        log.info("sign str == {}",stringBuilder.toString());
        log.info("sign == {}" , sign);
        return sign;
    }

    /**
     * get验签
     * @return
     */
    private String getSign(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("partner=")
                .append(partner)
                .append(key);
        String sign = Md5Utils.MD5_LOWERCASE(stringBuilder.toString());
        log.info("sign str == {}",stringBuilder.toString());
        log.info("sign == {}" , sign);
        return sign;
    }

    /**
     * get请求
     * @param param
     * @return
     */
    private String getSignAsc(JSONObject param){
        StringBuilder stringBuilder = new StringBuilder();
        param.put(headerParternerName,partner);
        param=changeLower(param);
        String paramString=getAsc(param);
        stringBuilder.append(paramString).append(key);
        String sign = Md5Utils.MD5_LOWERCASE(stringBuilder.toString());
        log.info("sign str == {}",stringBuilder.toString());
        log.info("sign == {}" , sign);
        return sign;
    }

    /**
     * get请求
     * @param param
     * @return
     */
    private String getSignAscToken(JSONObject param,String token){
        StringBuilder stringBuilder = new StringBuilder();
        param.put(headerParternerName,partner);
        param.put(headerTokenName,token);
        param=changeLower(param);
        String paramString=getAsc(param);
        stringBuilder.append(paramString).append(key);
        String sign = Md5Utils.MD5_LOWERCASE(stringBuilder.toString());
        log.info("sign str == {}",stringBuilder.toString());
        log.info("sign == {}" , sign);
        return sign;
    }

    /**
     * 根据key排序
     * @param json
     * @return
     */
    public String getAsc(JSONObject json) {
        if (json!=null){
            json.remove("token");
            json.remove("tranHeader");
        }
        String param="";
        Map<String, String> jsonMap = JSONObject.parseObject(json.toJSONString(), new TypeReference<Map<String, String>>(){});
        List<String> keys = new ArrayList<String>(jsonMap.keySet());
        Collections.sort(keys);
        LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
        for(int i=0;i<keys.size();i++){
            if(StringUtils.isNotEmpty(keys.get(i))){
                map.put(keys.get(i), jsonMap.get(keys.get(i)));
            }
            if(ObjectUtils.isEmpty(param)){
                param=param+keys.get(i)+"="+jsonMap.get(keys.get(i));
            }else {
                param=param+"&"+keys.get(i)+"="+jsonMap.get(keys.get(i));
            }
        }
        return param;
    }

    /**
     * key转换小写
     * @param jsonObject
     * @return
     */
    public JSONObject changeLower(JSONObject jsonObject){
        JSONObject json=new JSONObject();
        if(!ObjectUtils.isEmpty(jsonObject)){
            for(Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                json.put(entry.getKey().toLowerCase(),entry.getValue());
            }
        }
        return json;
    }

    /**
     * 获取盐
     * @return
     */
    public String getSalt(String phone){
        String salt="";
        String requestUrl = bxApiBaseUrl+BX_LOGIN_SALT;
        JSONObject param = new JSONObject();
        param.put("name", phone);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set(headerParternerName,partner);
        headers.set(headerSignName,getSignAsc(param));
        HttpEntity<JSONObject> entity = new HttpEntity<>(param, headers);
        requestUrl=requestUrl+"?name="+phone;
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, JSONObject.class);
        if(!ObjectUtils.isEmpty(exchange)){
            JSONObject bodyJson=exchange.getBody();
            if(bodyJson.containsKey("AppendData")){
                JSONObject dataJson=bodyJson.getJSONObject("AppendData");
                if(dataJson.containsKey("Salt")){
                    salt=dataJson.getString("Salt");
                }
            }
        }
        return salt;
    }

    /**
     * 密码加密
     * @return
     */
    public String getMd5Password(String phone,String password) {
        String digest="";
        String md5=Md5Utils.MD5_LOWERCASE(password);
        String salt=getSalt(phone);
        digest=Md5Utils.MD5_LOWERCASE(md5+salt);
        return digest;
    }

    public String getUrlParam(JSONObject jsonObject,String urlParam){
        if (!ObjectUtils.isEmpty(jsonObject)){
            List<String> keys = new ArrayList<String>(jsonObject.keySet());
            keys.remove("tranHeader");
            for(int i=0;i<keys.size();i++){
                if(i==0){
                    urlParam=urlParam+"?"+keys.get(i)+"="+jsonObject.get(keys.get(i));
                }else {
                    urlParam=urlParam+"&"+keys.get(i)+"="+jsonObject.get(keys.get(i));
                }
            }
        }
        return urlParam;
    }

    public static void main(String[] args) {
//        BxApiUpgradeUtils utils = new BxApiUpgradeUtils();
//        utils.bxApiBaseUrl = "http://47.94.40.214:8098/RegTest/";
//
//        //JSONObject jsonObject = utils.bxGsolAsync(dto);
//        //System.out.println(jsonObject.toJSONString());

        JSONObject param = new JSONObject();
        param.put("OrderCode", "25092705426210880");
        System.out.println(BxApiUpgradeUtils.getSign(param.toJSONString()));
    }

}
