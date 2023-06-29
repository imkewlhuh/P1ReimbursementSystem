package com.revature.security;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeDAO employeeDao;

    @Autowired
    public CustomUserDetailsService(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

    private Collection<GrantedAuthority> mapEmployeeToAuthority(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        return grantedAuthorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee e = employeeDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No User found"));

        return new User(e.getUsername(), e.getPassword(), mapEmployeeToAuthority(e.getRole()));
    }
}
