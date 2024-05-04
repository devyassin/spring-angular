package com.example.springangularjwtbakend.services;

import com.example.springangularjwtbakend.entities.Payement;
import com.example.springangularjwtbakend.entities.PayementStatus;
import com.example.springangularjwtbakend.entities.PayementType;
import com.example.springangularjwtbakend.entities.Student;
import com.example.springangularjwtbakend.repositery.PayementRepositery;
import com.example.springangularjwtbakend.repositery.StudentRepositery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class PaymentService {

    private StudentRepositery studentRepositery;
    private PayementRepositery payementRepositery;

    public PaymentService(StudentRepositery studentRepositery, PayementRepositery payementRepositery) {
        this.studentRepositery = studentRepositery;
        this.payementRepositery = payementRepositery;
    }

    public Payement savePayement(MultipartFile file , LocalDate date,
                                 double amount
            , PayementType type, String studentCode) throws IOException {

        Path folderPAth= Paths.get(System.getProperty("user.home"),"emsi-data","payments");
        if(!Files.exists(folderPAth)){
            Files.createDirectories(folderPAth);
        }
        String fileName= UUID.randomUUID().toString();
        Path filePath=Paths.get(System.getProperty("user.home"),"emsi-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student=studentRepositery.findByCode(studentCode);
        Payement payement=Payement.builder()
                .date(date).type(type).student(student)
                .amount(amount).status(PayementStatus.CREATED).file(filePath.toUri().toString())
                .build();
        return  payementRepositery.save(payement);
    }

    public byte[] getPayementFile(Long payementId) throws IOException {
        Payement payement=payementRepositery.findById(payementId).get();
        return  Files.readAllBytes(Path.of(URI.create(payement.getFile())));
    }

    public Payement updatePayementStatus(PayementStatus status,Long id){

        Payement payement=payementRepositery.findById(id).get();
        payement.setStatus(status);
        return payementRepositery.save(payement);
    }
}
