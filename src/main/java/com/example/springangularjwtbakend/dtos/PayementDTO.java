package com.example.springangularjwtbakend.dtos;

import com.example.springangularjwtbakend.entities.PayementStatus;
import com.example.springangularjwtbakend.entities.PayementType;
import com.example.springangularjwtbakend.entities.Student;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PayementDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PayementType type;
    private PayementStatus status;
}
