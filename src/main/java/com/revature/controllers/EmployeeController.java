package com.revature.controllers;

import com.revature.security.JwtGenerator;
import com.revature.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final JwtGenerator jwtGenerator;

    @Autowired
    public EmployeeController(EmployeeService employeeService, JwtGenerator jwtGenerator) {
        this.employeeService = employeeService;
        this.jwtGenerator = jwtGenerator;
    }
}
