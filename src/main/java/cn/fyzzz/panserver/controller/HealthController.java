package cn.fyzzz.panserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fyzzz
 * 2021/8/13 5:57 下午
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

}
