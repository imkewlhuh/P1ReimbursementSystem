package com.revature.controllers;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.RoleDAO;
import com.revature.dto.AuthResponseDTO;
import com.revature.dto.LoginDTO;
import com.revature.dto.RegisterDTO;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final RoleDAO roleDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtGenerator jwtGenerator;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, EmployeeDAO employeeDao, RoleDAO roleDao, StatusDAO statusDao, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.employeeDao = employeeDao;
        this.roleDao = roleDao;
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

        Role role = roleDao.getByName("Employee");
        //Todo finish up
        employeeDao.save(e);

        return new ResponseEntity<AuthResponseDTO>(new AuthResponseDTO(token), HttpStatus.OK);
    }
}
