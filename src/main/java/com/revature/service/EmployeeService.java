package com.revature.service;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeDAO employeeDao;

    private final ReimbursementDAO reimbursementDao;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDao, ReimbursementDAO reimbursementDao) {
        this.employeeDao = employeeDao;
        this.reimbursementDao = reimbursementDao;
    }

    //insert
    public Employee createEmployee(Employee employee) {
        Employee returnedEmployee = employeeDao.save(employee);

        if (returnedEmployee.getId() > 0) {
            //add
        }else {
            //failure
            //todo put worning log
        }
        return returnedEmployee;
    }

    //update
    public Employee updateEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    //delete:  pass in id
    public boolean deleteEmployeeById(int id){
        employeeDao.deleteById(id);
        return  !(employeeDao.existsById(id)); //confirm deletion or not
    }

    //get by id
    public Employee getEmployeeById(int id){
        return employeeDao.findById(id).orElseThrow(() -> new EmployeeNotFoundException("No employee found with id:" + id));
    }

    // get all employees
    public List<Employee> getAllEmployees(){
        return employeeDao.findAll();

    }

    public Employee findByEmployeeUsername(String username){
        return employeeDao.findByUsername(username).orElseThrow(() ->
                new EmployeeNotFoundException("No employee found with username" + username));
    }

    //Get all reimbursements to a person
    public List<Reimbursement> getReimbursementsByEmployeeId(int id) {

        Optional<Employee> returnedEmployee = employeeDao.findById(id);

        if (returnedEmployee.isPresent()) {
            return returnedEmployee.get().getReimbursements();
        } else {
            throw new EmployeeNotFoundException("No employee with id: " + id);
        }
    }

}
