package com.example.springangularjwtbakend.repositery;

import com.example.springangularjwtbakend.entities.Payement;
import com.example.springangularjwtbakend.entities.PayementStatus;
import com.example.springangularjwtbakend.entities.PayementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayementRepositery extends JpaRepository<Payement, Long> {

    List<Payement> findByStudentCode(String code);
    List<Payement> findByStatus(PayementStatus payementStatus);
    List<Payement> findByType(PayementType payementType);

}
