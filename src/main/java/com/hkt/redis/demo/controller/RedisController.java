package com.hkt.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/customer")
public class RedisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping({"", "/", "index"})
    public String index() {
        String id = redisTemplate.opsForValue().get("cim:test");
        System.out.println("Tony cim:test value >>>>> " + id);

        if (id != null) {
            return "view cim:test: " + id;
        } else {
            return "please view id first. link: /customer/view/1";
        }
    }

    /**
     * Save id value in redis
     */
    @GetMapping("/view/{id}")
    public String view(HttpServletRequest request, @PathVariable String id) {
        redisTemplate.opsForValue().set("cim:test", id);
        return "ok";
    }
}
