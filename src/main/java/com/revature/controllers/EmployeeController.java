package com.revature.controllers;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.security.JwtGenerator;
import com.revature.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Employee> getAllEmployeesHandler() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployeeByIdHandler(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployeeHandler(@RequestBody Employee e) {
        return employeeService.createEmployee(e);
    }

    @PutMapping
    public Employee updateEmployeeHandler(@RequestBody Employee e) {
        return employeeService.updateEmployee(e);
    }

    @DeleteMapping("{id}")
    public boolean deleteEmployeeHandler(@PathVariable("id") int id) { return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("{id}/reimbursements")
    public List<Reimbursement> getReimbursementsFromEmployeeHandler(@PathVariable("id") int id) {
        return employeeService.getReimbursementsByEmployeeId(id);
    }

}
