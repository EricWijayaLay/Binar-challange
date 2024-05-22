package com.binar.Batch7.Controller;

import com.binar.Batch7.Service.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

//@GetMapping
public class EmployeeAccountController {
    //
    @Autowired
    public EmployeeAccountService employeeAccountService;
}

