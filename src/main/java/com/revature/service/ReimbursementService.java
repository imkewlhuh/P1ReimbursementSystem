package com.revature.service;

import com.revature.daos.ReimbursementDAO;
import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReimbursementService {

    private final ReimbursementDAO reimbursementDao;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDao) {
        this.reimbursementDao = reimbursementDao;
    }
    //Gets all
    public List<Reimbursement> getAllReimbursements(){
        List<Reimbursement> reimbursements = reimbursementDao.findAll();
        return reimbursements;
    }

    //CREATE reimbursement
    public Reimbursement addReimbursement(Reimbursement reimbursement) {

        Reimbursement returnedReimbursement = reimbursementDao.save(reimbursement);

        if (returnedReimbursement.getId() > 0) {
            System.out.println("Reimbursement successfully added!");
        } else {
            System.out.println("Failed to add reimbursement");
        }

        return returnedReimbursement;
    }

    //UPDATE reimbursement
    public Reimbursement updateReimbursement(Reimbursement r) {
        return reimbursementDao.save((r));
    }

    //DELETE reimbursement
    public boolean deleteReimbursement(int id) {
        reimbursementDao.deleteById(id);

        return !(reimbursementDao.existsById(id));
    }

    //Find by ID
    public Reimbursement findReimbursementById(int id) {
        return reimbursementDao.findById(id).orElseThrow(() -> new ReimbursementNotFoundException("No course found with id: " + id));
    }

    //Search
    public List<Reimbursement> searchReimbursements(String searchPattern) {
        return reimbursementDao.findByDescriptionContainingIgnoreCase(searchPattern);
    }

}
