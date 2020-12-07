package com.example.controller;

import com.beust.jcommander.internal.Maps;
import com.example.model.GspRole;
import com.example.model.base.ResponseVo;
import com.example.service.HomeService;
import com.example.vo.BackendApi;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gsp/role")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/v1/add")
    @ApiOperation(value = "新增角色", httpMethod = "POST", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<String> findGspMenuPost(@RequestBody GspRole gspRole){
        return homeService.add(gspRole);
    }

    @GetMapping("/v1/find/{id}")
    @ApiOperation(value = "查询信息", httpMethod = "GET", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<GspRole> find(@RequestParam(name = "id") Long id){
       return homeService.find(id);
    }

    @GetMapping("/v1/find/api")
    @ApiOperation(value = "查询api", httpMethod = "GET", response = ResponseVo.class, produces = "application/json;charset=UTF-8")
    public ResponseVo<Object> findApi(){
        return homeService.findApi();
    }

    @Autowired
    DocumentationCache documentationCache;
    @Autowired
    ServiceModelToSwagger2Mapper mapper;

    @RequestMapping(
            value = "/api/updateApi",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Json> updateApi(
            @RequestParam(value = "group", required = false) String swaggerGroup) {

        // 加载已有的api
//        Map<String,Boolean> apiMap = Maps.newHashMap();
//        List<BackendApi> apis = backendApiRepository.findAll();
//        apis.stream().forEach(api->apiMap.put(api.getPath()+api.getMethod(),true));

        // 获取swagger
        String groupName = Optional.fromNullable(swaggerGroup).or(Docket.DEFAULT_GROUP_NAME);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        if (documentation == null) {
            return new ResponseEntity<Json>(HttpStatus.NOT_FOUND);
        }
        Swagger swagger = mapper.mapDocumentation(documentation);

        Map<String,Boolean> apiMap = Maps.newHashMap();

        // 加载到数据库
        for(Map.Entry<String, Path> item : swagger.getPaths().entrySet()){
            String path = item.getKey();
            Path pathInfo = item.getValue();
            createApiIfNeeded(apiMap, path,  pathInfo.getGet(), HttpMethod.GET.name());
            createApiIfNeeded(apiMap, path,  pathInfo.getPost(), HttpMethod.POST.name());
            createApiIfNeeded(apiMap, path,  pathInfo.getDelete(), HttpMethod.DELETE.name());
            createApiIfNeeded(apiMap, path,  pathInfo.getPut(), HttpMethod.PUT.name());
        }
        return new ResponseEntity<Json>(HttpStatus.OK);
    }

    private void createApiIfNeeded(Map<String, Boolean> apiMap, String path, Operation operation, String method) {
        if(operation==null) {
            return;
        }
        if(!apiMap.containsKey(path+ method)){
            apiMap.put(path+ method,true);

            BackendApi api = new BackendApi();
            api.setMethod(method);
            api.setOperationId(operation.getOperationId());
            api.setPath(path);
            api.setTag(operation.getTags().get(0));
            api.setSummary(operation.getSummary());
            System.out.println(api);
            // 保存
        }
    }

    @GetMapping(
            value = "/getApi")
    public Object getApi(
            @RequestParam(value = "group", required = false) String swaggerGroup) {
        String groupName = java.util.Optional.ofNullable(swaggerGroup).orElse(Docket.DEFAULT_GROUP_NAME);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        documentationCache.all();
        if (documentation == null) {
            return new ResponseEntity<Json>(HttpStatus.NOT_FOUND);
        }
        Swagger swagger = mapper.mapDocumentation(documentation);
        List<Map<String, String>> list = new ArrayList<>();
        for (Map.Entry<String, Path> item : swagger.getPaths().entrySet()) {
            String path = item.getKey();
            Path pathInfo = item.getValue();
            Map<String, String> getMap = getMap(path, pathInfo.getGet(), HttpMethod.GET.name());
            if (!getMap.isEmpty()){
                list.add(getMap);
            }
            Map<String, String> postMap = getMap(path, pathInfo.getPost(), HttpMethod.POST.name());
            if (!postMap.isEmpty()){
                list.add(postMap);
            }
            Map<String, String> deleteMap = getMap(path, pathInfo.getDelete(), HttpMethod.DELETE.name());
            if (!deleteMap.isEmpty()){
                list.add(deleteMap);
            }
            Map<String, String> putMap = getMap(path, pathInfo.getPut(), HttpMethod.PUT.name());
            if (!putMap.isEmpty()){
                list.add(putMap);
            }
        }
        return list;
    }

    private Map<String, String> getMap(String path, Operation operation, String method) {
        Map<String, String> map = new HashMap<>();
        if (operation == null) {
            return map;
        }
        map.put("method", method);
        map.put("operationId", operation.getOperationId());
        map.put("path", path);
        map.put("tag", operation.getTags().get(0));
        map.put("summary", operation.getSummary());
        return map;
    }


}
