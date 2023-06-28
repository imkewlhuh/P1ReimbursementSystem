package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @Length(min = 2, max = 20)
    private  String firstName;

    @Column(name = "last_name")
    @Length(min = 2, max = 20)
    private String lastName;

    @ManyToOne
    private Role role;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany
    private List<Reimbursement> reimbursements;
}
