package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reimbursements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reimbursement {

    @Id
    @Column(name = "reimbursements_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    public void setEmployee(Employee employee){

    }

    @Column(nullable = false, columnDefinition = "VARCHAR(255) default 'Pending'")
    private String status;

}
