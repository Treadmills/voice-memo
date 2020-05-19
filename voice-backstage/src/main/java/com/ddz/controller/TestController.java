package com.ddz.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2020/5/18.
 */
@Controller
@RequestMapping(value = "/test", produces = "application/json")
public class TestController {

    @Value("${server.port}")
    String port;

    @ApiOperation(value = "接口的功能介绍",notes = "提示接口使用者注意事项",httpMethod = "GET")
    @ApiImplicitParam(dataType = "string",name = "name",value = "姓名",required = true)
    @ResponseBody
    @RequestMapping(value = "/swagger")
    public String index(String name) {

        return "hello "+ name;
    }
}
