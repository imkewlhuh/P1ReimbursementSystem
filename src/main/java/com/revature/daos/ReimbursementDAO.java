package com.revature.daos;

import com.revature.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;
@Repository
public interface ReimbursementDAO extends JpaRepository {
    Reimbursement findByDescription(Spring description);

    List<Reimbursement> findByDescriptionContaininIgnoreCase(String pattern);

    @Query("FROM Reimbursement WHERE name Like %:pattern% ")

    List<Reimbursement>findByDescriptionSearch(@Param("pattern")String pattern);

}
