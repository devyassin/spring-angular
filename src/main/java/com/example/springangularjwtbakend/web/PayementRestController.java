package com.example.springangularjwtbakend.web;

import com.example.springangularjwtbakend.entities.Payement;
import com.example.springangularjwtbakend.entities.PayementStatus;
import com.example.springangularjwtbakend.entities.PayementType;
import com.example.springangularjwtbakend.entities.Student;
import com.example.springangularjwtbakend.repositery.PayementRepositery;
import com.example.springangularjwtbakend.repositery.StudentRepositery;
import com.example.springangularjwtbakend.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PayementRestController {

    private StudentRepositery studentRepositery;
    private PayementRepositery payementRepositery;
    private PaymentService paymentService;


    @GetMapping(path = "/payements")
    public List<Payement> allPayments(){
        return payementRepositery.findAll();
    }

    @GetMapping(path = "/payements/{id}")
    public Payement getPayementById(@PathVariable Long id){
        return payementRepositery.findById(id).get();
    }

    @GetMapping(path = "/payements/byStatus")
    public List<Payement> payementByStatus(@RequestParam PayementStatus payementStatus){
        return payementRepositery.findByStatus(payementStatus);
    }

    @GetMapping(path = "/students/{code}/payements")
    public List<Payement> payementByStudent(@PathVariable String code){
        return payementRepositery.findByStudentCode(code);
    }

    @GetMapping(path = "/students/byType")
    public List<Payement> payementByType(@RequestParam PayementType payementType){
        return payementRepositery.findByType(payementType);
    }



    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepositery.findAll();
    }

    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepositery.findByCode(code);
    }

    @GetMapping(path = "/studentsByProgrammId")
    public List<Student> getStudentsByProgramId(@RequestParam String programmId){
        return studentRepositery.findByProgramId(programmId);
    }

    @PutMapping("/payements/{id}")
    public Payement updatePayementStatus(@RequestParam PayementStatus status,@PathVariable Long id){

       return paymentService.updatePayementStatus(status,id);
    }

    @PostMapping(path = "/payements",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payement savePayement(@RequestParam MultipartFile file , LocalDate date,
                                 double amount
    ,PayementType type,String studentCode) throws IOException {

       return paymentService.savePayement(file,date,amount,type,studentCode);
    }

    @GetMapping(path = "/payementFile/{payementId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPayementFile(@PathVariable Long payementId) throws IOException {
      return  paymentService.getPayementFile(payementId);
    }
}
