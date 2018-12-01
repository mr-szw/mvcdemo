package com.mvc.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dawei 2018/11/17
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public String login(String token) {
        logger.info(">>>>>>>>>>>>>>>> {}", token);
        return "token+ " + token;
    }

}
