package com.binar.Batch7.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/employee")
public class EmployeeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeeController.class);
    @GetMapping(value = "")
    public String hello() {
        return "Hello World";
    }
}

