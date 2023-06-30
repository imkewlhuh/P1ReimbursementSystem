package com.revature.controllers;

import com.revature.daos.StatusDAO;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reimbursements")
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class ReimbursementController {

    private final ReimbursementService reimbursementService;
    private final EmployeeService employeeService;
    private final StatusDAO statusDao;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService, EmployeeService employeeService, StatusDAO statusDao) {
        this.reimbursementService = reimbursementService;
        this.employeeService = employeeService;
        this.statusDao = statusDao;
    }

    @GetMapping
    public List<Reimbursement> getAllReimbursementsHandler(@RequestParam(name = "search", required = false) String searchPattern) {

        if (searchPattern != null) {
            return reimbursementService.searchReimbursements(searchPattern);
        }

        return reimbursementService.getAllReimbursements();
    }

    @GetMapping("{id}")
    public Reimbursement findReimbursementByIdHandler(@PathVariable("id") int id) {
        return reimbursementService.findReimbursementById(id);
    }

    @PostMapping
    public Reimbursement createReimbursementHandler(@RequestBody Reimbursement r) {
        Status status = statusDao.getByState("pending");
        r.setStatus(status);
        return reimbursementService.addReimbursement(r);
    }

    @PutMapping
    public Reimbursement updateReimbursementHandler(@RequestBody Reimbursement r) {
        return reimbursementService.updateReimbursement(r);
    }

    @DeleteMapping("{id}")
    public boolean deleteReimbursementHandler(@PathVariable("id") int id) {
        return reimbursementService.deleteReimbursement(id);
    }
}
