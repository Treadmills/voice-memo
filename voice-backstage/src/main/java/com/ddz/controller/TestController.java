package com.ddz.controller;

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

  //  @ResponseBody
    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "voice") String name) {
        return "login";
    }
}
