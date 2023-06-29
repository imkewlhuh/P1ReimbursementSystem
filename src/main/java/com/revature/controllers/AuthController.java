package com.revature.controllers;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.StatusDAO;
import com.revature.dto.RegisterDTO;
import com.revature.models.Employee;
import com.revature.models.Status;
import com.revature.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//AuthController allows us to register and login different people
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final EmployeeDAO employeeDao;

    private final StatusDAO statusDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtGenerator jwtGenerator;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, EmployeeDAO employeeDao, StatusDAO statusDao, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.employeeDao = employeeDao;
        this.statusDao = statusDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        //checks for existence of username
        if(employeeDao.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<String>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        Employee e = new Employee();
        e.setFirstName(registerDTO.getFirstName());
        e.setLastName(registerDTO.getLastName());
        e.setUsername(registerDTO.getUsername());
        e.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Status status = StatusDAO.getByState("Employee");
        //Todo finish up




        return new ResponseEntity<>("Account Registration was successful", HttpStatus.CREATED);
    }
}
