package xyz.fjzkuroko.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2022/2/19 20:51
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/admin")
    public String index() {
        return "hello admin";
    }

    @GetMapping("/a")
    public String a() {
        return "hello a";
    }

    @GetMapping("/q/y/b")
    public String b1() {
        return "hello b1";
    }

    @GetMapping("/p/b")
    public String b2() {
        return "hello b2";
    }

    @RequestMapping("/login_page")
    public String root(){
        return "未登录";
    }
}
