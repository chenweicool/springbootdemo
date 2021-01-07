package com.mylog.logger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/index")
public class IndexLoggerController {

    private final static Logger logger = LoggerFactory.getLogger(IndexLoggerController.class);

    @GetMapping("/test")
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        logger.info("日志已经整合成功了");
        logger.error("日志整合失败");
        return modelAndView;
    }
}
