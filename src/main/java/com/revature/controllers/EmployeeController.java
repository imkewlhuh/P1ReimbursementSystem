package com.revature.controllers;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.RoleDAO;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.security.JwtGenerator;
import com.revature.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final RoleDAO roleDao;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleDAO roleDao, JwtGenerator jwtGenerator) {
        this.employeeService = employeeService;
        this.roleDao = roleDao;
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

    @GetMapping("/current/{username}")
    public Employee getEmployeeByUsernameHandler(@PathVariable("username") String username) {
        return employeeService.getEmployeeByUsername(username);
    }

    @PostMapping
    public Employee createEmployeeHandler(@RequestBody Employee e) {
        return employeeService.createEmployee(e);
    }

    @PutMapping
    public Employee updateEmployeeHandler(@RequestBody Employee e) {
        return employeeService.updateEmployee(e);
    }

    @PutMapping("{id}")
    public Employee promoteEmployeeHandler(@PathVariable int id) {
        Role role = roleDao.getByName("Manager");
        Employee promotedEmployee = employeeService.getEmployeeById(id);
        promotedEmployee.setRole(role);
        employeeService.updateEmployee(promotedEmployee);

        return promotedEmployee;
    }

    @DeleteMapping("{id}")
    public boolean deleteEmployeeHandler(@PathVariable("id") int id) { return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("{id}/reimbursements")
    public List<Reimbursement> getReimbursementsFromEmployeeHandler(@PathVariable("id") int id) {
        return employeeService.getReimbursementsByEmployeeId(id);
    }

}