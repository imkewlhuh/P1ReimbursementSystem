package com.revature.service;

import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

import java.util.List;

public class ReimbursementService {

    private final ReimbursementDAO reimbursementDao;

    public ReimbursementService(ReimbursementDAO reimbursementDao) {
        this.reimbursementDao = reimbursementDao;
    }
    //Gets all
    public List<Reimbursement> getAllReimbursements(){
        List<Reimbursement> reimbursements = reimbursementDao.findAll();
        return reimbursements;
    }



}
