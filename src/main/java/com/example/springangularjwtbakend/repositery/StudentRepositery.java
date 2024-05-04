package com.example.springangularjwtbakend.repositery;

import com.example.springangularjwtbakend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepositery extends JpaRepository<Student, Long> {
    Student findByCode(String code);
    List<Student> findByProgramId(String programId);

}
